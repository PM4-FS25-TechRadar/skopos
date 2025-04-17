<template>
  <div class="radar-card">
    <h3 class="title">{{ radar.name || 'Unnamed Radar' }}</h3>

    <div class="meta">
      <!-- quadrants -->
      <div class="block">
        <h4>Quadrants</h4>
        <ul>
          <li v-for="(q,i) in radar.quadrants" :key="i">
            {{ q.name || `Quadrant ${i+1}` }}
          </li>
        </ul>
      </div>

      <!-- rings (outer➝inner) -->
      <div class="block">
        <h4>Rings</h4>
        <ul>
          <li
              v-for="(r,i) in [...radar.rings].reverse()"
              :key="i"
          >
            {{ r.name || `Ring ${radar.rings.length-i}` }}
          </li>
        </ul>
      </div>
    </div>

    <div class="actions">
      <button class="edit-btn" @click="$emit('edit', radar)">Edit</button>
      <button class="delete-btn" @click="ask=true">🗑️</button>
    </div>

    <ConfirmModal
        v-if="ask"
        title="Delete radar?"
        @cancel="ask=false"
        @confirm="remove"
    />
  </div>
</template>

<script>
import ConfirmModal from '@/components/ui/ConfirmModal.vue'
import service      from '@/services/radarService'

export default {
  components:{ ConfirmModal },
  props:['radar'],
  emits:['deleted','edit'],
  data:()=>({ ask:false }),
  methods:{
    async remove(){
      this.ask=false
      await service.remove(this.radar.id)
      this.$emit('deleted', this.radar.id)
    }
  }
}
</script>

<style src="./radarCard.css"></style>
