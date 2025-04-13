<template>
  <div class="entries-container">
    <button class="add-new-entry-btn" @click="createNewEntry">+</button>
    <div class="entries-list">
      <RadarEntry
          v-for="entry in entries"
          :key="entry.id"
          :entry="entry"
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
  components: {
    RadarEntry
  },
  data() {
    return {
      entries: []
    }
  },
  created() {
    this.fetchEntries()
  },
  methods: {
    fetchEntries() {
      fetch('/radar/entries')
          .then(res => res.json())
          .then(data => {
            this.entries = data
          })
          .catch(err => console.error('Failed to fetch entries:', err))
    },
    createNewEntry() {
      const newEntry = {
        id: null,
        label: '',
        quadrant: 'data',
        ring: 'adopt',
        moved: 0,
        year: new Date().getFullYear()
      }
      this.entries.unshift(newEntry)
    },
    updateEntry(updatedEntry) {
      const index = this.entries.findIndex(entry => entry.id === updatedEntry.id)
      if (index !== -1) {
        this.entries.splice(index, 1, updatedEntry)
      }
    },
    removeEntry(id) {
      this.entries = this.entries.filter(entry => entry.id !== id)
    },
    addEntryToList(newEntry) {
      this.entries = this.entries.map(entry => entry.id === null ? newEntry : entry)
    }
  }
}
</script>

<style>
.entries-container {
  display: flex;
  flex-direction: column;
}
.add-new-entry-btn {
  position: fixed;
  bottom: 10px;
  right: 10px;
  font-size: 24px;
  padding: 0;
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