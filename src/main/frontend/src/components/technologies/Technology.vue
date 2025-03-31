<template>
  <div class="technology-card">
    <button
        class="delete-btn"
        title="Delete Technology"
        @click="deleteTechnology"
        aria-label="Delete Technology"
    >
      🗑️
    </button>
    <input
        v-model="localTechnology.name"
        class="technology-name-input"
        type="text"
        placeholder="Technology Name"
    />
    <label>
      Description
      <textarea v-model="localTechnology.description" placeholder="Description"></textarea>
    </label>
    <div class="versions-section">
      <h4>Versions</h4>
      <div
          v-for="(version, index) in localTechnology.versions"
          :key="version.id || 'new-' + index"
          class="version-item"
      >
        <label>
          Version Name
          <input v-model="version.name" type="text" placeholder="Version Name" />
        </label>
        <label>
          Version Description
          <textarea v-model="version.description" placeholder="Version Description"></textarea>
        </label>
        <button @click="removeVersion(index)">Remove Version</button>
      </div>
      <button @click="addVersion">Add Version</button>
    </div>
    <button class="save-button" @click="saveTechnology">Save</button>
  </div>
</template>

<script>
export default {
  props: ['technology'],
  data() {
    return {
      localTechnology: {
        ...this.technology,
        versions: this.technology.versions ? [...this.technology.versions] : []
      }
    };
  },
  methods: {
    saveTechnology() {
      const method = this.localTechnology.id ? 'PUT' : 'POST';
      const url = this.localTechnology.id
          ? `/technologies/${this.localTechnology.id}`
          : '/technologies';

      fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.localTechnology)
      })
          .then((res) => {
            if (!res.ok) {
              return res.text().then((text) => {
                throw new Error(text || 'Save failed');
              });
            }
            return res.json();
          })
          .then((technology) => {
            if (method === 'POST') {
              this.$emit('add-technology', technology);
            } else {
              this.$emit('update-technology', technology);
            }
          })
          .catch((err) => {
            console.error('Save failed:', err);
            alert(`Fehler beim Speichern: ${err.message}`);
          });
    },
    deleteTechnology() {
      if (!this.localTechnology.id) {
        this.$emit('deleted', this.localTechnology.id);
        return;
      }

      fetch(`/technologies/${this.localTechnology.id}`, {
        method: 'DELETE'
      })
          .then((res) => {
            if (res.ok) {
              this.$emit('deleted', this.localTechnology.id);
            } else {
              console.error('Delete failed');
              alert('Löschen fehlgeschlagen');
            }
          })
          .catch((err) => {
            console.error('Delete failed:', err);
            alert('Fehler beim Löschen');
          });
    },
    addVersion() {
      this.localTechnology.versions.push({ name: '', description: '' });
    },
    removeVersion(index) {
      this.localTechnology.versions.splice(index, 1);
    }
  }
};
</script>

<style src="./technology.css"></style>