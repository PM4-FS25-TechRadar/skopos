-- TECHNOLOGIES
INSERT INTO technologies (name, description) VALUES ('MariaDB', 'Open Source relational database, fork of MySQL');
INSERT INTO technologies (name, description) VALUES ('Hibernate', 'ORM framework for Java to map objects to database tables');
INSERT INTO technologies (name, description) VALUES ('PostgreSQL', 'Powerful, open-source object-relational database');
INSERT INTO technologies (name, description) VALUES ('Grafana', 'Visualization tool for metrics and logs');
INSERT INTO technologies (name, description) VALUES ('Neo4J', 'Popular graph database for connected data');
INSERT INTO technologies (name, description) VALUES ('JSONB', 'Binary JSON storage in PostgreSQL');
INSERT INTO technologies (name, description) VALUES ('MongoDB', 'NoSQL document database');
INSERT INTO technologies (name, description) VALUES ('Redis', 'In-memory key-value data store');
INSERT INTO technologies (name, description) VALUES ('RabbitMQ', 'Message broker for asynchronous communication');
INSERT INTO technologies (name, description) VALUES ('DDD', 'Domain-Driven Design - modeling complex software');
INSERT INTO technologies (name, description) VALUES ('TDD', 'Test-Driven Development methodology');
INSERT INTO technologies (name, description) VALUES ('Cypress', 'End-to-end testing tool for web applications');
INSERT INTO technologies (name, description) VALUES ('Scrum', 'Agile framework for managing projects');
INSERT INTO technologies (name, description) VALUES ('TestRail', 'Test case management tool');
INSERT INTO technologies (name, description) VALUES ('ADR', 'Architectural Decision Records documentation');
INSERT INTO technologies (name, description) VALUES ('Figma', 'Collaborative interface design tool');
INSERT INTO technologies (name, description) VALUES ('Confluence', 'Documentation and collaboration platform');
INSERT INTO technologies (name, description) VALUES ('Jira', 'Issue tracking and agile project management tool');
INSERT INTO technologies (name, description) VALUES ('TechRadar', 'Internal tool for visualizing tech strategy');
INSERT INTO technologies (name, description) VALUES ('Arc42', 'Architecture documentation template');
INSERT INTO technologies (name, description) VALUES ('NewRelic', 'Application performance monitoring platform');
INSERT INTO technologies (name, description) VALUES ('Sentry', 'Error tracking and monitoring platform');
INSERT INTO technologies (name, description) VALUES ('Kibana', 'Visualization tool for Elasticsearch data');
INSERT INTO technologies (name, description) VALUES ('ScanMeter', 'Custom tool for scanning metrics');
INSERT INTO technologies (name, description) VALUES ('Trunk Based', 'Branching model in software version control');
INSERT INTO technologies (name, description) VALUES ('FeatureToggle', 'Feature flags to control feature rollout');
INSERT INTO technologies (name, description) VALUES ('ContDelivery', 'Continuous Delivery practices and tools');
INSERT INTO technologies (name, description) VALUES ('GrpaphQL', 'API query language and runtime');
INSERT INTO technologies (name, description) VALUES ('Altair GrpaphQL', 'GraphQL client for testing and debugging');
INSERT INTO technologies (name, description) VALUES ('REST', 'Architectural style for web services');
INSERT INTO technologies (name, description) VALUES ('OpenAPI', 'Specification for defining REST APIs');
INSERT INTO technologies (name, description) VALUES ('SOAP', 'XML-based messaging protocol');
INSERT INTO technologies (name, description) VALUES ('SharedDB', 'Pattern for sharing databases between services');
INSERT INTO technologies (name, description) VALUES ('RBAC', 'Role-Based Access Control');
INSERT INTO technologies (name, description) VALUES ('ABAC', 'Attribute-Based Access Control');
INSERT INTO technologies (name, description) VALUES ('OpenID Connect', 'Authentication layer on top of OAuth 2.0');
INSERT INTO technologies (name, description) VALUES ('OAuth', 'Authorization framework for delegated access');
INSERT INTO technologies (name, description) VALUES ('jwt', 'JSON Web Token - compact token format');
INSERT INTO technologies (name, description) VALUES ('CertBased Auth', 'Authentication using certificates');
INSERT INTO technologies (name, description) VALUES ('Strangler', 'Incremental legacy system replacement pattern');
INSERT INTO technologies (name, description) VALUES ('FeatureFlag', 'Control features with runtime flags');
INSERT INTO technologies (name, description) VALUES ('Citadelle', 'Internal architectural framework');
INSERT INTO technologies (name, description) VALUES ('Gateway', 'API Gateway pattern for routing requests');
INSERT INTO technologies (name, description) VALUES ('Microservice', 'Microservices architecture pattern');
INSERT INTO technologies (name, description) VALUES ('Java', 'Popular object-oriented programming language');
INSERT INTO technologies (name, description) VALUES ('JIB', 'Build container images for Java apps');
INSERT INTO technologies (name, description) VALUES ('Spring', 'Java application framework');
INSERT INTO technologies (name, description) VALUES ('Angular', 'Web application framework by Google');
INSERT INTO technologies (name, description) VALUES ('VueJS', 'Progressive frontend framework');
INSERT INTO technologies (name, description) VALUES ('PHP', 'Scripting language for web development');
INSERT INTO technologies (name, description) VALUES ('Zend', 'PHP framework');
INSERT INTO technologies (name, description) VALUES ('Laminas', 'Successor of Zend Framework');
INSERT INTO technologies (name, description) VALUES ('Keycloak', 'Identity and access management solution');
INSERT INTO technologies (name, description) VALUES ('Quarkus', 'Kubernetes-native Java framework');
INSERT INTO technologies (name, description) VALUES ('OpenShift', 'RedHat’s Kubernetes-based platform');
INSERT INTO technologies (name, description) VALUES ('Virtual Machines', 'Virtualized computing environments');
INSERT INTO technologies (name, description) VALUES ('GKE', 'Google Kubernetes Engine');
INSERT INTO technologies (name, description) VALUES ('Jenkins', 'Automation server for CI/CD');
INSERT INTO technologies (name, description) VALUES ('Selenium', 'Web browser automation framework');

-- VERSIONS
INSERT INTO versions (name, description, technology_id) VALUES ('Java 17', 'Java SE 17 LTS release', (SELECT id FROM technologies WHERE name = 'Java'));
INSERT INTO versions (name, description, technology_id) VALUES ('Java 11', 'Java SE 11 LTS release', (SELECT id FROM technologies WHERE name = 'Java'));
INSERT INTO versions (name, description, technology_id) VALUES ('OpenShift 3', 'OpenShift version 3.x', (SELECT id FROM technologies WHERE name = 'OpenShift'));
INSERT INTO versions (name, description, technology_id) VALUES ('OpenShift 4', 'OpenShift version 4.x', (SELECT id FROM technologies WHERE name = 'OpenShift'));
