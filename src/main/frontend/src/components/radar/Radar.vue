<template>
  <div class="radar-wrapper">
    <div class="radar-logo" />

    <h1 class="radar-title"></h1>

    <svg id="radar"></svg>

    <div class="radar-info" v-if="payload">
      <div class="ring-description" v-for="ring in payload.rings" :key="ring.id">
        <div v-if="ring.description">
          <h2>{{ ring.name }} {{ ring.order }}</h2>
          <p>{{ ring.description }}</p>
        </div>
      </div>

      <div class="quadrant-description" v-for="quadrant in payload.quadrants" :key="quadrant.id">
        <div v-if="quadrant.description">
          <h2>{{ quadrant.name }} {{ quadrant.order }}</h2>
          <p>{{ quadrant.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import './radar.css'
import radarService from "@/services/radarService.js";

export default {
  data() {
    return {
      radarTitle: '',
      payload: null
    }
  },
  async mounted() {
    const id = this.$route.params.id
    const json = await radarService.getRadar(id)
    this.radarTitle = json.title
    this.payload = json
    this.renderRadar(json)
  },
  methods: {
    renderRadar(data) {
      const style = getComputedStyle(document.documentElement)
      const bg    = style.getPropertyValue('--radar-background')
      const grid  = style.getPropertyValue('--brand-main')
      const inactive = style.getPropertyValue('--radar-inactive')

      const ringColors = ['--ring-inner','--ring-second','--ring-third','--ring-outer']
      const rings = data.rings.map((r,i) => ({
        name:  r.name.toUpperCase(),
        color: style.getPropertyValue(ringColors[i])
      }))

      radar_visualization({
        svg_id: "radar",
        width: 1450,
        height: 1000,
        colors: {
          background: bg,
          grid: grid,
          inactive: inactive
        },
        title: data.title,
        quadrants: data.quadrants,
        rings: rings,
        print_layout: true,
        entries: data.entries,
      })
    }
  }
}
</script>
