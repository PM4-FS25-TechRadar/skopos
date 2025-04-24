<template>
  <div class="container">
    <input v-model="radar.name" class="name" placeholder="Radar name"/>

    <div class="body">
      <svg
          class="radar-preview"
          :viewBox="`-${c} -${c} ${size} ${size}`"
          :width="size"
          :height="size"
      >
        <!-- rings -->
        <circle
            v-for="i in 4"
            :key="'ring'+i"
            :class="'ring ring-'+i"
            :r="step*i"
        />

        <!-- axes -->
        <line class="axis" :x1="-c" :x2="c" y1="0" y2="0"/>
        <line class="axis" x1="0" x2="0" :y1="-c" :y2="c"/>

        <!-- ring labels (inner → outer) -->
        <text
            v-for="(ring,i) in radar.rings"
            :key="'label'+i"
            :class="'ring-label rl-'+i"
            x="0"
            :y="-(step*(i+1)-step/2)"
        >
          {{ (ring.name || defaultRingName(i)).toUpperCase() }}
        </text>

        <!-- quadrant labels -->
        <text
            v-for="(q,i) in radar.quadrants"
            :key="'q'+i"
            :x="labels[i].x"
            :y="labels[i].y"
            class="quad-label"
        >
          {{ q.name || `Q${i + 1}` }}
        </text>
      </svg>

      <div class="lists">
        <!-- rings -->
        <section class="editor-block">
          <h4>Rings</h4>
          <RingListEditor
              :rings="radar.rings"
              @update:rings="radar.rings = $event"
          />
        </section>
        <!-- quadrants -->
        <section class="editor-block">
          <h4>Quadrants</h4>
          <QuadrantListEditor
              :quadrants="radar.quadrants"
              @update:quadrants="radar.quadrants = $event"
          />
        </section>
      </div>
    </div>

    <div class="actions">
      <button class="save" @click="save">Save</button>
      <button class="cancel" @click="$emit('close', null)">Cancel</button>
    </div>

    <ConfirmModal
        v-if="error"
        title="Save failed"
        confirm-only
        confirm-label="Close"
        @confirm="error = ''"
    >
    </ConfirmModal>
  </div>
</template>

<script>
import RingListEditor from './editors/RingListEditor.vue'
import QuadrantListEditor from './editors/QuadrantListEditor.vue'
import ConfirmModal from '@/components/ui/ConfirmModal.vue'
import service from '@/services/radarService'
import {deepClone} from '@/utils/deepClone'

export default {
  components: {RingListEditor, QuadrantListEditor, ConfirmModal},
  props: {modelValue: Object, size: {default: 600}},
  emits: ['update:modelValue', 'close'],
  data() {
    return {
      radar: deepClone(this.modelValue),
      error: ''
    }
  },
  watch: {
    modelValue(v) {
      this.radar = deepClone(v)
    }
  },
  computed: {
    c() {
      return this.size / 2
    },
    step() {
      return this.size / 8
    },
    labels() {
      const h = this.c / 2
      return [
        {x: -h, y: -h},
        {x: h, y: -h},
        {x: -h, y: h},
        {x: h, y: h}
      ]
    }
  },
  methods: {
    defaultRingName(i) {
      return ['ADOPT', 'TRIAL', 'EVAL', 'HOLD'][i] || `RING ${i + 1}`
    },
    defaultQuadrantName(i) {
      return this.modelValue.quadrants[i]?.name || `Quadrant ${i + 1}`
    },
    async save() {
      this.radar.rings.forEach((r, i) => {
        if (!r.name.trim()) {
          r.name = this.defaultRingName(i)
        }
      })
      this.radar.quadrants.forEach((q, i) => {
        if (!q.name.trim()) {
          q.name = this.defaultQuadrantName(i)
        }
      })
      try {
        const saved = await service.save(this.radar)
        this.$emit('update:modelValue', saved)
        this.$emit('close', saved)
      } catch (e) {
        this.error = e.message || 'Unknown error'
      }
    }
  }
}
</script>

<style src="./radarEditor.css"></style>
