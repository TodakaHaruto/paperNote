CREATE TABLE IF NOT EXISTS users (
    user_serial BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    birthday DATE,
    gender INTEGER
);


CREATE TABLE IF NOT EXISTS papers (
paper_id BIGSERIAL PRIMARY KEY,
user_serial BIGINT NOT NULL,
title TEXT NOT NULL,
paper_url TEXT NOT NULL,
read_date DATE,
learning_note TEXT,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_papers_user
FOREIGN KEY (user_serial)
REFERENCES users(user_serial)
ON DELETE CASCADE
);

CREATE TABLE paper_citations (
citation_id BIGSERIAL PRIMARY KEY,
citing_paper_id BIGINT NOT NULL,
cited_paper_id BIGINT NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_paper_citations_citing
FOREIGN KEY (citing_paper_id)
REFERENCES papers(paper_id)
ON DELETE CASCADE,
CONSTRAINT fk_paper_citations_cited
FOREIGN KEY (cited_paper_id)
REFERENCES papers(paper_id)
ON DELETE CASCADE,
CONSTRAINT uq_paper_citations
UNIQUE (citing_paper_id, cited_paper_id),
CONSTRAINT chk_paper_citations_not_self
CHECK (citing_paper_id <> cited_paper_id)
);