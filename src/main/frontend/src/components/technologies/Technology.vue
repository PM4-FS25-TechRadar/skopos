<template>
  <div class="card technology-card">
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
      <div class="versions-container">
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
          <div class="version-actions">
            <button class="btn" @click="saveVersion(version, index)">Save</button>
            <button class="btn" @click="removeVersion(version, index)">Remove</button>
          </div>
        </div>
        <div class="add-version-container">
          <button class="btn" @click="addVersion">Add Version</button>
        </div>
      </div>
    </div>
    <button class="btn btn-primary save-button" @click="saveTechnology">Save Technology</button>
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
      if (!this.localTechnology.versions || this.localTechnology.versions.length === 0) {
        alert('Bitte mindestens eine Version eintragen.');
        return;
      }

      if (this.localTechnology.versions.some(v => !v.name || !v.name.trim())) {
        alert('Jede Version muss einen Namen haben.');
        return;
      }

      const method = this.localTechnology.id ? 'PUT' : 'POST';
      const url = this.localTechnology.id
          ? `/technologies/${this.localTechnology.id}`
          : '/technologies';

      fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          name: this.localTechnology.name,
          description: this.localTechnology.description
        })
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
            this.localTechnology.id = technology.id;
            if (method === 'POST') {
              this.$emit('add-technology', this.localTechnology);
            } else {
              this.$emit('update-technology', this.localTechnology);
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

    saveVersion(version, index) {
      if (!this.localTechnology.id) {
        alert('Bitte speichern Sie zuerst die Technologie.');
        return;
      }

      const method = version.id ? 'PUT' : 'POST';
      const url = version.id
          ? `/technologies/${this.localTechnology.id}/versions/${version.id}`
          : `/technologies/${this.localTechnology.id}/versions`;

      fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          name: version.name,
          description: version.description
        })
      })
          .then((res) => {
            if (!res.ok) {
              return res.text().then((text) => {
                throw new Error(text || 'Version save failed');
              });
            }
            return res.json();
          })
          .then(result => {
            if (result.id) {
              this.localTechnology.versions[index].id = result.id;
            }
          })
          .catch((err) => {
            console.error('Version save failed:', err);
            alert(`Fehler beim Speichern der Version: ${err.message}`);
          });
    },

    removeVersion(version, index) {
      if (version.id) {
        fetch(`/technologies/${this.localTechnology.id}/versions/${version.id}`, {
          method: 'DELETE'
        })
            .then((res) => {
              if (res.ok) {
                this.localTechnology.versions.splice(index, 1);
              } else {
                console.error('Version delete failed');
                alert('Löschen der Version fehlgeschlagen');
              }
            })
            .catch((err) => {
              console.error('Version delete failed:', err);
              alert('Fehler beim Löschen der Version');
            });
      } else {
        this.localTechnology.versions.splice(index, 1);
      }
    }
  }
};
</script>

<style src="./technology.css"></style>