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

INSERT INTO dev.races (
    race_id,
    race_name,
    circuit_id,
    season,
    date,
    time,
    laps,
    winner_id,
    status
) VALUES
(1, 'Australian Grand Prix', 1, 2025, '2025-03-16 05:00:00', '05:00:00', 58, 0, 'Scheduled'),
(2, 'Chinese Grand Prix', 2, 2025, '2025-03-23 14:00:00', '14:00:00', 56, 0, 'Scheduled'),
(3, 'Japanese Grand Prix', 3, 2025, '2025-04-06 14:00:00', '14:00:00', 53, 0, 'Scheduled'),
(4, 'Bahrain Grand Prix', 4, 2025, '2025-04-13 18:00:00', '18:00:00', 57, 0, 'Scheduled'),
(5, 'Saudi Arabian Grand Prix', 5, 2025, '2025-04-20 20:00:00', '20:00:00', 50, 0, 'Scheduled'),
(6, 'Miami Grand Prix', 6, 2025, '2025-05-04 16:30:00', '16:30:00', 57, 0, 'Scheduled'),
(7, 'Emilia Romagna Grand Prix', 7, 2025, '2025-05-18 15:00:00', '15:00:00', 63, 0, 'Scheduled'),
(8, 'Monaco Grand Prix', 8, 2025, '2025-05-25 14:00:00', '14:00:00', 78, 0, 'Scheduled'),
(9, 'Spanish Grand Prix', 9, 2025, '2025-06-01 15:00:00', '15:00:00', 66, 0, 'Scheduled'),
(10, 'Canadian Grand Prix', 10, 2025, '2025-06-15 14:00:00', '14:00:00', 70, 0, 'Scheduled'),
(11, 'Austrian Grand Prix', 11, 2025, '2025-06-29 15:00:00', '15:00:00', 71, 0, 'Scheduled'),
(12, 'British Grand Prix', 12, 2025, '2025-07-06 15:00:00', '15:00:00', 52, 0, 'Scheduled'),
(13, 'Belgian Grand Prix', 13, 2025, '2025-07-27 15:00:00', '15:00:00', 44, 0, 'Scheduled'),
(14, 'Hungarian Grand Prix', 14, 2025, '2025-08-03 15:00:00', '15:00:00', 70, 0, 'Scheduled'),
(15, 'Dutch Grand Prix', 15, 2025, '2025-08-31 15:00:00', '15:00:00', 72, 0, 'Scheduled'),
(16, 'Italian Grand Prix', 16, 2025, '2025-09-07 15:00:00', '15:00:00', 53, 0, 'Scheduled'),
(17, 'Azerbaijan Grand Prix', 17, 2025, '2025-09-21 15:00:00', '15:00:00', 51, 0, 'Scheduled'),
(18, 'Singapore Grand Prix', 18, 2025, '2025-10-05 20:00:00', '20:00:00', 61, 0, 'Scheduled'),
(19, 'United States Grand Prix', 19, 2025, '2025-10-19 14:00:00', '14:00:00', 56, 0, 'Scheduled'),
(20, 'Mexico City Grand Prix', 20, 2025, '2025-10-26 14:00:00', '14:00:00', 71, 0, 'Scheduled'),
(21, 'Brazilian Grand Prix', 21, 2025, '2025-11-09 14:00:00', '14:00:00', 71, 0, 'Scheduled'),
(22, 'Las Vegas Grand Prix', 22, 2025, '2025-11-22 22:00:00', '22:00:00', 50, 0, 'Scheduled'),
(23, 'Qatar Grand Prix', 23, 2025, '2025-11-30 17:00:00', '17:00:00', 57, 0, 'Scheduled'),
(24, 'Abu Dhabi Grand Prix', 24, 2025, '2025-12-07 14:00:00', '14:00:00', 55, 0, 'Scheduled');


GRANT USAGE ON SCHEMA dev TO postgres;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA dev TO postgres;

ALTER DEFAULT PRIVILEGES IN SCHEMA dev
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO postgres;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA dev TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA dev
GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO postgres;