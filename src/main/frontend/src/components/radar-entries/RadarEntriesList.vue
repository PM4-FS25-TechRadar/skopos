<template>
  <div class="entries-container">
    <RadarEntry v-for="entry in entries" :key="entry.id" :entry="entry" />
  </div>
</template>

<script>
import RadarEntry from './RadarEntry.vue'

export default {
  components: { RadarEntry },
  data() {
    return {
      entries: []
    }
  },
  mounted() {
    fetch('/radar/entries')
        .then(res => res.json())
        .then(data => this.entries = data.sort((a, b) => a.label.localeCompare(b.label)))
        .catch(err => console.error('Failed to load radar entries:', err))
  },
  methods: {
    updateEntry(updated) {
      const idx = this.entries.findIndex(e => e.id === updated.id)
      if (idx !== -1) {
        this.entries[idx] = updated
      }
    }
  }
}
</script>

<style src="./RadarEntriesList.css"></style>
