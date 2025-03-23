<template>
  <svg id="radar"></svg>
</template>

<script>
export default {
  mounted() {
    fetch('/radar/data/2022')
        .then((res) => res.json())
        .then((loadedStuff) => {
          this.renderRadar(loadedStuff)
        })
        .catch(err => console.error('Radar fetch failed:', err))
  },
  methods: {
    renderRadar(loadedStuff) {
      radar_visualization({
        svg_id: "radar",
        width: 1450,
        height: 1000,
        colors: {
          background: "#c5c5c5",
          grid: "#cd5b1c",
          inactive: "#5fa1b7"
        },
        title: "Sobrado Radar",
        quadrants: [
          { name: "Platforms - 0 ↘️" },
          { name: "Patterns - 1 ↙️" },
          { name: "Methodologies and Metrics - 2 ↖️" },
          { name: "Data and Reporting - 3 ↗️" }
        ],
        rings: [
          { name: "ADOPT - 0", color: "#cd5b1c" },
          { name: "TRIAL - 1", color: "#5fa1b7" },
          { name: "EVAL - 2", color: "#693e52" },
          { name: "HOLD - 3", color: "#000000" }
        ],
        print_layout: true,
        entries: loadedStuff
      })
    }
  }
}
</script>
