<template>
  <div class="entry-card">
    <button class="delete-btn" title="Eintrag löschen" @click="deleteEntry">🗑️</button>

    <input
        v-model="localEntry.label"
        class="entry-label-input"
        type="text"
    />

    <label>
      Quadrant
      <select v-model="localEntry.quadrant">
        <option>data</option>
        <option>platforms</option>
        <option>patterns</option>
        <option>methods</option>
      </select>
    </label>

    <label>
      Ring
      <select v-model="localEntry.ring">
        <option>adopt</option>
        <option>trial</option>
        <option>eval</option>
        <option>hold</option>
      </select>
    </label>

    <label>
      Moved
      <input v-model="localEntry.moved" type="number"/>
    </label>

    <label>
      Year
      <input v-model="localEntry.year" type="number"/>
    </label>

    <button class="save-button" @click="saveEntry">Save</button>
  </div>
</template>


<script>
export default {
  props: ['entry'],
  data() {
    return {
      localEntry: {...this.entry}
    }
  },
  methods: {
    saveEntry() {
      fetch(`/radar/entries/${this.localEntry.id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(this.localEntry)
      })
          .then(res => {
            if (!res.ok) throw new Error('Update failed')
            return res.json()
          })
          .then(data => this.$emit('update-entry', data))
          .catch(err => console.error(err))
    },
    deleteEntry() {
      fetch(`/radar/entries/${this.localEntry.id}`, {
        method: 'DELETE'
      })
          .then((res) => {
            if (res.ok) {
              this.$emit('deleted', this.localEntry.id) // optional: dem Parent sagen
            } else {
              console.error('Löschen fehlgeschlagen')
            }
          })
          .catch((err) => console.error('Löschen fehlgeschlagen:', err))
    }
  }
}
</script>

<style src="./radarEntry.css"></style>
