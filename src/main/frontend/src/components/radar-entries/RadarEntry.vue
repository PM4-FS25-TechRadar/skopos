<template>
  <div class="entry-card">
    <button class="delete-btn" title="Eintrag löschen" @click="deleteEntry">🗑️</button>

    <label>
      Technology
      <select v-model="localEntry.technologyId" @change="loadVersionsForTech">
        <option disabled value="">Bitte wählen</option>
        <option v-for="tech in technologies" :key="tech.id" :value="tech.id">
          {{ tech.name }}
        </option>
      </select>
    </label>

    <label>
      Version
      <select v-model="localEntry.versionId" :disabled="!versions.length">
        <option disabled value="">Bitte wählen</option>
        <option v-for="version in versions" :key="version.id" :value="version.id">
          {{ version.name }}
        </option>
      </select>
    </label>
    <label>
      Ring
      <select v-model="localEntry.ringId">
        <option v-for="ring in radar.rings" :key="ring.id" :value="ring.id">
          {{ ring.name }}
        </option>
      </select>
    </label>

    <label>
      Quadrant
      <select v-model="localEntry.quadrantId">
        <option v-for="quad in radar.quadrants" :key="quad.id" :value="quad.id">
          {{ quad.name }}
        </option>
      </select>
    </label>

    <label>
      Status
      <select v-model="localEntry.status">
        <option>NEW</option>
        <option>NO_CHANGE</option>
        <option>MOVED_UP</option>
        <option>MOVED_DOWN</option>
      </select>
    </label>

    <button class="save-button" @click="saveEntry">Save</button>
  </div>
</template>

<script>
export default {
  props: {
    entry: Object,
    radar: Object
  },
  data() {
    return {
      technologies: [],
      versions: [],
      localEntry: {
        id: null,
        technologyId: '',
        versionId: '',
        ringId: null,
        quadrantId: null,
        status: 'NEW'
      }
    }
  },
  watch: {
    entry: {
      handler(newEntry) {
        const techId = newEntry.version?.technology?.id || null

        this.localEntry = {
          id: newEntry.id || null,
          label: newEntry.label || '',
          technologyId: techId,
          versionId: newEntry.version?.id || newEntry.versionId || '',
          ringId: newEntry.ring?.id || newEntry.ringId || null,
          quadrantId: newEntry.quadrant?.id || newEntry.quadrantId || null,
          status: newEntry.status || 'NEW'
        }

        if (techId) {
          this.loadVersionsForTech()
        }
      },
      immediate: true,
      deep: true
    }
  },
  mounted() {
    this.loadTechnologies()
  },
  methods: {
    async loadTechnologies() {
      try {
        const res = await fetch('/technologies')
        this.technologies = await res.json()
      } catch (err) {
        console.error('Failed to load technologies:', err)
      }
    },
    async loadVersionsForTech() {
      if (!this.localEntry.technologyId) return
      try {
        const res = await fetch(`/technologies/${this.localEntry.technologyId}/versions`)
        this.versions = await res.json()
      } catch (err) {
        console.error('Failed to load versions:', err)
        this.versions = []
      }
    },
    saveEntry() {
      const isNew = !this.localEntry.id
      const method = isNew ? 'PUT' : 'POST'
      const url = isNew
          ? `/radar/${this.radar.id}/entries`
          : `/entries/${this.localEntry.id}`

      const payload = {
        id: this.localEntry.id,
        label: this.localEntry.label,
        versionId: this.localEntry.versionId,
        ringId: this.localEntry.ringId,
        quadrantId: this.localEntry.quadrantId,
        status: this.localEntry.status
      }

      fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      })
          .then(res => {
            if (!res.ok) throw new Error('Save failed')
            return res.json()
          })
          .then(data => {
            this.$emit(isNew ? 'add-entry' : 'update-entry', data)
          })
          .catch(err => console.error(err))
    },
    deleteEntry() {
      if (!this.localEntry.id) {
        this.$emit('deleted', null)
        return
      }

      fetch(`/entries/${this.localEntry.id}`, {
        method: 'DELETE'
      })
          .then(res => {
            if (res.ok) {
              this.$emit('deleted', this.localEntry.id)
            } else {
              console.error('Delete failed')
            }
          })
          .catch(err => console.error('Delete failed:', err))
    }
  }
}
</script>

<style src="./radarEntry.css"></style>
