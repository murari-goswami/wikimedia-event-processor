arangodb-startup:
	docker run -p 8529:8529 -e ARANGO_ROOT_PASSWORD=openSesame arangodb/arangodb:3.10.0

.PHONY: source destination clean