<template>
  <div class="radar-editor-container">
    <!-- Editable Radar Name -->
    <input v-model="localRadar.name" class="radar-name-input" placeholder="Radar Name"/>

    <div class="editor-content">
      <!-- Radar SVG Display -->
      <div class="radar-svg">
        <svg :width="size" :height="size" viewBox="-220 -220 440 440">
          <!-- Rings -->
          <circle
              v-for="(ring, idx) in localRings"
              :key="'ring' + ring._id"
              :r="(idx + 1) * 50"
              fill="none"
              stroke="#cd5b1c"
              stroke-width="1"
          />

          <!-- Axes -->
          <line x1="-200" x2="200" stroke="#cd5b1c"/>
          <line y1="-200" y2="200" stroke="#cd5b1c"/>

          <!-- Quadrant Labels -->
          <text
              v-for="(quad, idx) in localQuadrants"
              :key="'quadlabel' + quad._id"
              :x="quadLabelPos[idx].x"
              :y="quadLabelPos[idx].y"
              fill="#333"
              font-size="14"
              font-weight="bold"
              text-anchor="middle"
          >
            {{ quad.name || 'Quadrant ' + (idx + 1) }}
          </text>

          <!-- Ring Labels -->
          <text
              v-for="(ring, idx) in localRings"
              :key="'ringlabel' + ring._id"
              :y="-(idx + 1) * 50"
              x="0"
              text-anchor="middle"
              fill="#666"
              font-size="12"
          >
            {{ ring.name || 'Ring ' + (idx + 1) }}
          </text>
        </svg>
      </div>

      <!-- Quadrant & Ring Editors -->
      <div class="editor-panel">
        <div class="editor-pair">
          <section class="edit-section">
            <h4>Quadrants</h4>
            <draggable v-model="localQuadrants" item-key="_id" class="drag-list" handle=".drag-handle">
              <template #item="{ element }">
                <div class="drag-item">
                  <span class="drag-handle">☰</span>
                  <input v-model="element.name" placeholder="Quadrant Name" class="drag-input"/>
                </div>
              </template>
            </draggable>
          </section>

          <section class="edit-section">
            <h4>Rings</h4>
            <draggable v-model="reversedRings" item-key="_id" class="drag-list" handle=".drag-handle">
              <template #item="{ element }">
                <div class="drag-item">
                  <span class="drag-handle">☰</span>
                  <input v-model="element.name" placeholder="Ring Name" class="drag-input"/>
                </div>
              </template>
            </draggable>
          </section>
        </div>
      </div>
    </div>

    <!-- Action Buttons -->
    <div class="editor-actions">
      <button class="save-button" @click="saveRadar">Save</button>
      <button class="cancel-button" @click="cancelEdit">Cancel</button>
    </div>
  </div>
</template>

<script>
import draggable from 'vuedraggable'

export default {
  components: { draggable },
  props: {
    modelValue: Object,
    size: { type: Number, default: 600 }
  },
  data() {
    return {
      localRadar: {},
      localRings: [],
      localQuadrants: [],
      quadLabelPos: [
        { x: -100, y: -100 },
        { x: 100, y: -100 },
        { x: -100, y: 100 },
        { x: 100, y: 100 }
      ]
    }
  },
  computed: {
    // Display the rings reversed (outermost first), but keep internal order correct
    reversedRings: {
      get() {
        return [...this.localRings].reverse()
      },
      set(newVal) {
        this.localRings = [...newVal].reverse()
      }
    }
  },
  watch: {
    modelValue: {
      handler(v) {
        this.localRadar = JSON.parse(JSON.stringify(v))
        this.localRings = JSON.parse(JSON.stringify(v.rings))
        this.localQuadrants = JSON.parse(JSON.stringify(v.quadrants))
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    saveRadar() {
      this.localRadar.rings = this.localRings
      this.localRadar.quadrants = this.localQuadrants

      const method = this.localRadar.id ? 'PUT' : 'POST'
      const url = this.localRadar.id ? `/radars/${this.localRadar.id}` : '/radars'

      fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.localRadar)
      })
          .then(res => res.json())
          .then(data => {
            // Fetch the full radar after saving (with rings/quadrants)
            return fetch(`/radars/${data.id}`)
                .then(res => res.json())
                .then(fullRadar => {
                  this.$emit('update:modelValue', fullRadar)
                  this.$emit('close-editor', fullRadar)
                })
          })
          .catch(err => {
            console.error('Save failed:', err)
            alert('Error saving Radar')
          })
    },
    cancelEdit() {
      this.$emit('close-editor', null)
    }
  }
}
</script>

<style src="./radarEditor.css"></style>
