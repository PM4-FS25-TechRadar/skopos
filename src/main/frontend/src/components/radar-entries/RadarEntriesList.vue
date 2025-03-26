<template>
  <div class="entries-container">
    <RadarEntry
        v-for="entry in entries"
        :key="entry.id"
        :entry="entry"
        @deleted="removeEntry"
    />
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
    removeEntry(id) {
      this.entries = this.entries.filter(e => e.id !== id)
    }
  }
}
</script>

<style src="./radarEntriesList.css"></style>
