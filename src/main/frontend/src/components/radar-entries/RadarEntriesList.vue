<template>
  <div class="entries-container">
    <button class="add-new-entry-btn" @click="createNewEntry">+</button>
    <div class="entries-list">
      <RadarEntry
          v-for="entry in entries"
          :key="entry.id || entry.tempId"
          :entry="entry"
          :radar="radar"
          @update-entry="updateEntry"
          @deleted="removeEntry"
          @add-entry="addEntryToList"
      />
    </div>
  </div>
</template>

<script>
import RadarEntry from './RadarEntry.vue'

export default {
  props: ['radar'],
  components: { RadarEntry },
  data() {
    return {
      entries: [],
      tempIdCounter: 1
    }
  },
  watch: {
    radar: {
      handler(newRadar) {
        if (newRadar?.id) {
          this.fetchEntries(newRadar.id)
        }
      },
      immediate: true
    }
  },
  methods: {
    fetchEntries(radarId) {
      fetch(`/radar/${radarId}/entries`)
          .then(res => res.json())
          .then(data => {
            this.entries = data
          })
          .catch(err => console.error('Failed to fetch entries:', err))
    },
    createNewEntry() {
      const newEntry = {
        id: null,
        tempId: 'temp-' + this.tempIdCounter++,
        ringId: null,
        quadrantId: null,
        versionId: null,
        status: 'NEW'
      }
      this.entries.unshift(newEntry)
    },
    updateEntry(updatedEntry) {
      const index = this.entries.findIndex(entry =>
          entry.id === updatedEntry.id || entry.tempId === updatedEntry.tempId
      )
      if (index !== -1) {
        this.entries.splice(index, 1, updatedEntry)
      }
    },
    removeEntry(idOrNull) {
      this.entries = this.entries.filter(entry =>
          entry.id !== idOrNull && entry.tempId !== idOrNull
      )
    },
    addEntryToList(newEntry) {
      this.entries = this.entries.map(entry =>
          !entry.id ? newEntry : entry
      )
    }
  }
}
</script>

<style>
.entries-container {
  margin-top: 2rem;
  display: flex;
  flex-direction: column;
}
.add-new-entry-btn {
  align-self: flex-end;
  margin-bottom: 1rem;
  font-size: 24px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #ff7f0e;
  color: white;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s;
}
.add-new-entry-btn:hover {
  background-color: #cc660b;
}
.entries-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
</style>
