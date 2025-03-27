let request = new XMLHttpRequest();
request.open('GET', "/radar/data/2022");
request.responseType = 'application/json';

request.onload = function () {
    loadedStuff = JSON.parse(request.response);
    renderRadar(loadedStuff);
};

request.send();

function renderRadar(loadedStuff) {
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
            {name: "Platforms - 0 ↘️"},
            {name: "Patterns - 1 ↙️"},
            {name: "Methodologies and Metrics - 2 ↖️"},
            {name: "Data and Reporting - 3 ↗️"}
        ],
        rings: [
            {name: "ADOPT - 0", color: "#cd5b1c"},
            {name: "TRIAL - 1", color: "#5fa1b7"},
            {name: "EVAL - 2", color: "#693e52"},
            {name: "HOLD - 3", color: "#000000"}
        ],
        print_layout: true,
        entries: loadedStuff
        //from SQL
        //[{"label" : "MariaDB", "quadrant" : 3, "ring" : 0, "moved" : 0, "active" : "true"}, {"label" : "REST", "quadrant" : 1, "ring" : 0, "moved" : 0, "active" : "true"}, {"label" : "Citadelle", "quadrant" : 1, "ring" : 0, "moved" : 1, "active" : "true"}, {"label" : "DDD", "quadrant" : 2, "ring" : 0, "moved" : 1, "active" : "true"}, {"label" : "Scrum", "quadrant" : 2, "ring" : 0, "moved" : 0, "active" : "true"}, {"label" : "Spring", "quadrant" : 0, "ring" : 0, "moved" : 0, "active" : "true"}, {"label" : "PHP", "quadrant" : 0, "ring" : 0, "moved" : -1, "active" : "true"}, {"label" : "Java", "quadrant" : 0, "ring" : 0, "moved" : 1, "active" : "true"}, {"label" : "Angular", "quadrant" : 0, "ring" : 0, "moved" : 0, "active" : "true"}, {"label" : "OpenShift", "quadrant" : 0, "ring" : 0, "moved" : 0, "active" : "true"}, {"label" : "Strangler", "quadrant" : 1, "ring" : 0, "moved" : 1, "active" : "true"}, {"label" : "GrpaphQL", "quadrant" : 1, "ring" : 0, "moved" : 0, "active" : "true"}, {"label" : "chart.js", "quadrant" : 0, "ring" : 1, "moved" : 1, "active" : "true"}, {"label" : "ReactJS", "quadrant" : 0, "ring" : 1, "moved" : 1, "active" : "true"}, {"label" : "TDD", "quadrant" : 2, "ring" : 1, "moved" : 1, "active" : "true"}, {"label" : "SharedDB", "quadrant" : 1, "ring" : 1, "moved" : 1, "active" : "true"}, {"label" : "PostgreSQL", "quadrant" : 3, "ring" : 2, "moved" : 1, "active" : "true"}, {"label" : "Keycloak", "quadrant" : 0, "ring" : 2, "moved" : 1, "active" : "true"}, {"label" : "Neo4J", "quadrant" : 3, "ring" : 2, "moved" : 0, "active" : "true"}, {"label" : "MongoDB", "quadrant" : 3, "ring" : 3, "moved" : -1, "active" : "true"}]
    });
}
