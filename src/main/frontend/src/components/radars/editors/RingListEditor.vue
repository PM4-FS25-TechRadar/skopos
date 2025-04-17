<template>
  <draggable v-model="ui" item-key="_id" handle=".handle" class="list">
    <template #item="{ element }">
      <div class="item">
        <span class="handle">☰</span>
        <input v-model="element.name" placeholder="Ring name" />
      </div>
    </template>
  </draggable>
</template>

<script>
import draggable from 'vuedraggable'
export default {
  components: { draggable },
  props: ['rings'],
  emits: ['update:rings'],
  computed: {
    ui: {
      get() {
        return [...this.rings].reverse()
      },
      set(v) {
        this.$emit('update:rings', [...v].reverse())
      }
    }
  }
}
</script>

<style src="./listEditor.css"></style>
