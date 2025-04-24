export default {
    async list ()      { return (await fetch('/radars')).json() },
    async get  (id)    { return (await fetch(`/radars/${id}`)).json() },

    async save (radar) {
        const method = radar.id ? 'PUT' : 'POST'
        const url    = radar.id ? `/radars/${radar.id}` : '/radars'
        const { id } = await fetch(url, {
            method,
            headers:{ 'Content-Type':'application/json' },
            body: JSON.stringify(radar)
        }).then(r => r.json()) // backend only returns { id }
        return this.get(id)  // pull full object w/ rings & quadrants
    },

    async remove (id)  { await fetch(`/radars/${id}`, { method:'DELETE' }) }
}
