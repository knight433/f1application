-- Create the dev schema
CREATE SCHEMA IF NOT EXISTS dev;

--@block
CREATE TABLE IF NOT EXISTS dev.driver (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    age INTEGER NOT NULL,
    origin VARCHAR(64) NOT NULL,
    team VARCHAR(64) NOT NULL,
    number INTEGER NOT NULL,
    world_championships INTEGER DEFAULT 0,
    debut_year INTEGER,
    is_active BOOLEAN DEFAULT TRUE,
    races_entered INTEGER DEFAULT 0,
    races_started INTEGER DEFAULT 0,
    wins INTEGER DEFAULT 0,
    podiums INTEGER DEFAULT 0,
    pole_positions INTEGER DEFAULT 0,
    fastest_laps INTEGER DEFAULT 0,
    career_points NUMERIC(10, 2) DEFAULT 0.00,
    last_race VARCHAR(128)
);


--@block
CREATE TABLE IF NOT EXISTS dev.track (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    location VARCHAR(128) NOT NULL,
    country VARCHAR(64) NOT NULL,
    length_km NUMERIC(5,3) NOT NULL,  
    number_of_laps INTEGER NOT NULL,
    total_distance_km NUMERIC(6,3),         
    lap_record_time VARCHAR(16),            
    lap_record_driver VARCHAR(64),
    lap_record_year INTEGER,
    first_grand_prix_year INTEGER,
    is_active BOOLEAN DEFAULT TRUE
);

--@block
CREATE TABLE IF NOT EXISTS dev.team (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    base_location VARCHAR(128) NOT NULL,
    country VARCHAR(64) NOT NULL,
    team_principal VARCHAR(64),
    power_unit_supplier VARCHAR(64),
    first_entry_year INTEGER,
    championships_won INTEGER DEFAULT 0,
    pole_positions INTEGER DEFAULT 0,
    wins INTEGER DEFAULT 0,
    fastest_laps INTEGER DEFAULT 0,
    podiums INTEGER DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE
);

--@block
CREATE TABLE IF NOT EXISTS dev.user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(64) UNIQUE NOT NULL,
    email VARCHAR(128) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    full_name VARCHAR(128),
    country VARCHAR(64),
    role VARCHAR(32) DEFAULT 'fan',  --'admin', 'fan', 'moderator'
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

GRANT USAGE ON SCHEMA dev TO postgres;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA dev TO postgres;

ALTER DEFAULT PRIVILEGES IN SCHEMA dev
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO postgres;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA dev TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA dev
GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO postgres;