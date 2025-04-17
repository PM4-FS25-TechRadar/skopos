<template>
  <div class="radars-container">
    <button v-if="!editingRadar" class="add-new-radar-btn" @click="addNew">＋</button>

    <div v-if="loading" class="loading">Loading…</div>

    <div v-if="!editingRadar" class="radars-list">
      <Radars
          v-for="(r, idx) in radars"
          :key="r.id ?? 'n-' + idx"
          :radar="r"
          @update-radar="updateRadar"
          @add-radar="replaceStub(idx, $event)"
          @deleted="removeRadar"
          @edit-radar="startEdit"
      />
    </div>

    <RadarEditor
        v-else
        v-model="editingRadar"
        @update:modelValue="updateRadar"
        @close-editor="handleEditorClose"
    />
  </div>
</template>

<script>
import Radars from './Radars.vue'
import RadarEditor from '@/components/radars/RadarEditor.vue'

const genId = () => crypto.randomUUID?.() || Math.random().toString(36).slice(2)

export default {
  components: { Radars, RadarEditor },
  data() {
    return {
      radars: [],
      loading: true,
      editingRadar: null
    }
  },
  created() {
    this.reloadRadars()
  },
  methods: {
    reloadRadars() {
      this.loading = true
      fetch('/radars')
          .then(res => res.json())
          .then(data => (this.radars = data))
          .finally(() => (this.loading = false))
    },
    addNew() {
      const blank = {
        name: '',
        description: '',
        quadrants: Array.from({ length: 4 }, () => ({ name: '', _id: genId() })),
        rings: Array.from({ length: 4 }, () => ({ name: '', _id: genId() }))
      }
      this.radars.unshift(blank)
      this.editingRadar = blank
    },
    startEdit(radar) {
      this.editingRadar = JSON.parse(JSON.stringify(radar))
    },
    updateRadar(updated) {
      const i = this.radars.findIndex(r => r.id === updated.id)
      if (i !== -1) {
        this.radars.splice(i, 1, updated)
      } else {
        this.radars.unshift(updated)
      }
    },
    replaceStub(index, newRadar) {
      this.radars.splice(index, 1, newRadar)
    },
    removeRadar(id) {
      this.radars = this.radars.filter(r => r.id !== id)
    },
    handleEditorClose(updatedRadar) {
      this.editingRadar = null

      if (updatedRadar?.id) {
        // Remove any stub (no id) radar from the list
        this.radars = this.radars.filter(r => r.id && r.id !== updatedRadar.id)

        this.radars.unshift(updatedRadar)
      } else {
        this.reloadRadars()
      }
    }
  }
}
</script>

<style src="./radarsList.css"></style>
