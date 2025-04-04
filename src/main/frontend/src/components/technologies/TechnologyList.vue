<template>
  <div class="technologies-container">
    <button
        class="add-new-technology-btn"
        @click="createNewTechnology"
        aria-label="Add new technology"
    >
      +
    </button>
    <div v-if="loading" class="loading">Loading...</div>
    <div v-else class="technologies-list">
      <Technology
          v-for="technology in technologies"
          :key="technology.id || 'new-' + technologies.indexOf(technology)"
          :technology="technology"
          @update-technology="updateTechnology"
          @deleted="removeTechnology"
          @add-technology="addTechnologyToList"
      />
    </div>
  </div>
</template>

<script>
import Technology from '@/components/technologies/Technology.vue';

export default {
  components: { Technology },
  data() {
    return {
      technologies: [],
      loading: true
    };
  },
  created() {
    this.fetchTechnologies();
  },
  methods: {
    fetchTechnologies() {
      fetch('/technologies')
          .then((res) => {
            if (!res.ok) {
              throw new Error('Failed to fetch technologies');
            }
            return res.json();
          })
          .then((data) => {
            if (Array.isArray(data)) {
              this.technologies = data;
            } else {
              console.error('Received non-array data:', data);
              this.technologies = [];
            }
          })
          .catch((err) => {
            console.error('Failed to fetch technologies:', err);
            this.technologies = [];
          })
          .finally(() => {
            this.loading = false;
          });
    },
    createNewTechnology() {
      const newTechnology = {
        id: null,
        name: '',
        description: '',
        versions: []
      };
      this.technologies.unshift(newTechnology);
    },
    updateTechnology(updatedTechnology) {
      const index = this.technologies.findIndex((t) => t.id === updatedTechnology.id);
      if (index !== -1) {
        this.technologies.splice(index, 1, updatedTechnology);
      }
    },
    removeTechnology(id) {
      this.technologies = this.technologies.filter((t) => t.id !== id);
    },
    addTechnologyToList(newTechnology) {
      const index = this.technologies.findIndex((t) => t.id === null);
      if (index !== -1) {
        this.technologies.splice(index, 1, newTechnology);
      } else {
        this.technologies.push(newTechnology);
      }
    }
  }
};
</script>

<style src="./technologyList.css"></style>