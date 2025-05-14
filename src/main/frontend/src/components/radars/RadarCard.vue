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
      <button class="duplicate-btn" @click="duplicate">Duplicate</button>
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
import {deepClone} from "@/utils/deepClone.js";

export default {
  components:{ ConfirmModal },
  props:['radar'],
  emits:['deleted','edit'],
  data:()=>({ ask:false }),
  methods:{
    goToRadar(){
      this.$router.push({ name: 'radar', params: { id: this.radar.id } })
    },
    duplicate(){
      const clone = deepClone(this.radar)
      delete clone.id
      clone.quadrants.forEach(q => delete q.id)
      clone.rings.forEach(r => delete r.id)

      if (clone.entries) {
        clone.entries = this.radar.entries.map(e => ({
          label: e.label,
          quadrant: e.quadrant,
          ring: e.ring,
          moved: e.moved,
          active: e.active,
          year: e.year,
        }))
        clone.entries.forEach(e => delete e.id)
      }
      console.log(clone)
      this.$emit('edit', clone)
    },
    async remove(){
      this.ask=false
      await service.remove(this.radar.id)
      this.$emit('deleted', this.radar.id)
    }
  }
}
</script>

<style src="./radarCard.css"></style>
