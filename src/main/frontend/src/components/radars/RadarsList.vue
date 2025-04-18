<template>
  <div class="wrapper">
    <button v-if="!editing" class="add-new-radar-btn" @click="newRadar">＋</button>

    <RadarEditor
        v-if="editing"
        :modelValue="editing"
        @update:modelValue="updateList"
        @close="handleEditorClose"
    />

    <div v-else class="radars-list">
      <RadarCard
          v-for="r in radars"
          :key="r.id ?? r._stubId"
          :radar="r"
          @edit="editing = clone(r)"
          @deleted="remove"
      />
    </div>

    <div v-if="loading" class="loading">Loading…</div>
  </div>
</template>

<script>
import { genId } from '@/utils/uuid'
import service from '@/services/radarService'
import RadarCard from '@/components/radars/RadarCard.vue'
import RadarEditor from '@/components/radars/RadarEditor.vue'

export default {
  components: { RadarCard, RadarEditor },
  data() {
    return { radars: [], loading: false, editing: null }
  },
  created() {
    this.reload()
  },
  methods: {
    async reload() {
      this.loading = true
      this.radars = await service.list()
      this.loading = false
    },
    clone(o) {
      return JSON.parse(JSON.stringify(o))
    },
    newRadar() {
      this.editing = {
        _stubId: genId(),
        name: '',
        rings: Array.from({ length: 4 }, () => ({ _id: genId(), name: '' })),
        quadrants: Array.from({ length: 4 }, () => ({ _id: genId(), name: '' }))
      }
    },
    updateList(saved) {
      this.radars = this.radars.filter(r => r._stubId !== saved._stubId)
      const i = this.radars.findIndex(r => r.id === saved.id)
      i === -1 ? this.radars.unshift(saved) : this.radars.splice(i, 1, saved)
    },
    remove(id) {
      this.radars = this.radars.filter(r => r.id !== id)
    },
    async handleEditorClose(updatedRadar) {
      this.editing = null
      await this.reload()
    }
  }
}
</script>

<style src="./radarsList.css"></style>