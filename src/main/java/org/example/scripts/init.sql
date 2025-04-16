CREATE TABLE IF NOT EXISTS users (
    id INTEGER AUTOINCREMENT PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    senha TEXT NOT NULL,
    createAt date not null,
    updateAt date not null
)