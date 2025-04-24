export const genId = () =>
    crypto.randomUUID?.() || Math.random().toString(36).slice(2)