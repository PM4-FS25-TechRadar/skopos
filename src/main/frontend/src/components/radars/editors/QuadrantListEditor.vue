<template>
  <draggable v-model="model" item-key="_id" handle=".handle" class="list">
    <template #item="{ element }">
      <div class="item">
        <span class="handle">☰</span>
        <input v-model="element.name" placeholder="Quadrant name" />
      </div>
    </template>
  </draggable>
</template>

<script>
import draggable from 'vuedraggable'
export default {
  components: { draggable },
  props: ['quadrants'],
  emits: ['update:quadrants'],
  computed: {
    model: {
      get() {
        return this.quadrants
      },
      set(v) {
        this.$emit('update:quadrants', v)
      }
    }
  }
}
</script>

<style src="./listEditor.css"></style>
