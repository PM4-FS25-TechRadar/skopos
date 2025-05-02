<template>
  <div class="radar-wrapper">
    <img alt="radar-img" class="radar-logo"
         src="https://landing.sobrado.ch/wp-content/themes/sobrado/assets/img/logo-white.svg"/>
    <h1 class="radar-title">Sobrado Tech Radar</h1>

    <p class="radar-desc">
      Shows Items that are relevant to Sobrado. Inspired and powered by Zalandos wonderful
      <a href="https://opensource.zalando.com/tech-radar/" target="_blank">Techradar</a>,
      with inputs from
      <a href="https://www.thoughtworks.com/radar" target="_blank">ThoughtWorks</a> and
      <a href="https://tarmac.io/techradar.html" target="_blank">Tarmac</a>.
    </p>

    <svg id="radar"></svg>

    <div class="radar-info">
      <h2>Adopt 0</h2>
      <p>We feel strongly that we should be adopting these items...</p>

      <h2>Trial 1</h2>
      <p>Worth pursuing. It is important to understand how to build up this capability...</p>

      <h2>Eval 2</h2>
      <p>Worth exploring with the goal of understanding how it will affect us...</p>

      <h2>Hold 3</h2>
      <p>Proceed with caution. Should not be extended or used for new projects...</p>
    </div>
  </div>
</template>

<script>
import './radar.css'

export default {
  data() {
    return {
      radarTitle: '',
      payload: null
    }
  },
  mounted() {
    fetch('/radars/data/2022')
        .then(res => res.json())
        .then(json => {
          this.payload = json;
          this.radarTitle = json.title;
          this.renderRadar(json)
        })
        .catch(err => console.error('Radar fetch failed:', err))
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
