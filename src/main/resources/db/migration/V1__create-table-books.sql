CREATE TABLE IF NOT EXISTS books (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    publicationDate DATE,
    synopsis TEXT
);