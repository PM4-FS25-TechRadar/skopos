<template>
  <div class="radar-card">
    <div class="view-mode">
      <button
          class="delete-btn"
          title="Delete Radar"
          @click="deleteRadar"
          aria-label="Delete Radar"
      >🗑️
      </button>

      <h3>{{ radar.name || 'New Radar' }}</h3>

      <div class="section">
        <h4 class="section-title">Quadrants</h4>
        <ul class="quadrants">
          <li v-for="(q, i) in radar.quadrants" :key="q._id">
            {{ q.name || `Quadrant ${i + 1}` }}
          </li>
        </ul>
      </div>

      <div class="section">
        <h4 class="section-title">Rings</h4>
        <ul class="rings">
          <li v-for="(r, i) in radar.rings" :key="r._id">
            {{ r.name || `Ring ${i + 1}` }}
          </li>
        </ul>
      </div>
      <button class="edit-btn" @click="$emit('edit-radar', radar)">Edit</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RadarCard',
  props: ['radar'],
  emits: ['update-radar', 'add-radar', 'deleted', 'edit-radar'],
  methods: {
    deleteRadar() {
      if (!this.radar.id) {
        this.$emit('deleted', this.radar.id)
        return
      }

      const confirmed = confirm(`Are you sure you want to delete "${this.radar.name}"?`);
      if (!confirmed) return;

      fetch(`/radars/${this.radar.id}`, {method: 'DELETE'})
          .then(res => {
            if (!res.ok) throw new Error('Delete failed')
            this.$emit('deleted', this.radar.id)
          })
          .catch(err => {
            console.error('Delete failed:', err)
            alert('Error deleting radar')
          })
    }
  }
}
</script>

<style src="./radars.css"></style>
