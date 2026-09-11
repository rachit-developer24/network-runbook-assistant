CREATE EXTENSION IF NOT EXISTS vector;

CREATE EXTENSION IF NOT EXISTS hstore;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE vector_store (
                              id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
                              content TEXT,
                              metadata JSON,
                              embedding VECTOR(384)
);
