<template>
  <div class="entry-card">
    <button class="delete-btn" title="Eintrag löschen" @click="deleteEntry">🗑️</button>

    <input
        v-model="localEntry.label"
        class="entry-label-input"
        type="text"
        placeholder="Label (optional)"
    />

    <label>
      Version
      <select v-model="localEntry.versionId">
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
      versions: [],
      localEntry: {
        id: null,
        label: '',
        ringId: null,
        quadrantId: null,
        versionId: null,
        status: 'NEW'
      }
    }
  },
  watch: {
    entry: {
      handler(newEntry) {
        this.localEntry = {
          id: newEntry.id || null,
          label: newEntry.label || '',
          ringId: newEntry.ring?.id || newEntry.ringId || null,
          quadrantId: newEntry.quadrant?.id || newEntry.quadrantId || null,
          versionId: newEntry.version?.id || newEntry.versionId || null,
          status: newEntry.status || 'NEW'
        }
      },
      immediate: true,
      deep: true
    }
  },
  mounted() {
    this.loadVersions()
  },
  methods: {
    async loadVersions() {
      try {
        const techsRes = await fetch('/api/technologies')
        const techs = await techsRes.json()

        const versionPromises = techs.map(t =>
            fetch(`/api/technologies/${t.id}/versions`).then(r => r.json())
        )
        const allVersionsArrays = await Promise.all(versionPromises)
        this.versions = allVersionsArrays.flat()
      } catch (err) {
        console.error('Failed to load versions:', err)
      }
    },
    saveEntry() {
      const isNew = !this.localEntry.id
      const method = isNew ? 'PUT' : 'POST'
      const url = isNew
          ? `/api/radar/${this.radar.id}/entries`
          : `/api/entries/${this.localEntry.id}`

      fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.localEntry)
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

      fetch(`/api/entries/${this.localEntry.id}`, {
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
