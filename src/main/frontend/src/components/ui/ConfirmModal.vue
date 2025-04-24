<template>
  <div class="backdrop" @click="handleBackdrop">
    <div class="modal" @click.stop>
      <h3>{{ title }}</h3>
      <slot />

      <div class="row">
        <!-- cancel only if not confirm‑only -->
        <button
            v-if="!confirmOnly"
            @click="$emit('cancel')"
        >{{ cancelLabel }}</button>

        <!-- always show the confirm button, styled differently -->
        <button
            :class="[ confirmOnly ? 'primary' : 'danger' ]"
            @click="$emit('confirm')"
        >{{ confirmLabel }}</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title:        { type: String, required: true },
    confirmOnly:  { type: Boolean, default: false },
    confirmLabel: { type: String, default: 'Confirm' },
    cancelLabel:  { type: String, default: 'Cancel' }
  },
  emits: ['confirm','cancel'],
  methods: {
    handleBackdrop() {
      // if it's an OK‑only alert, clicking backdrop also dismisses
      this.confirmOnly ? this.$emit('confirm') : this.$emit('cancel')
    }
  }
}
</script>

<style src="./confirmModal.css"></style>
