
WITH mock_data(id, name, date_of_birth, surname_ipa) AS (
    SELECT *
    FROM (VALUES
        (1, 'William Shatner',  '1931-03-22', NULL),
        (2, 'Leonard Nimoy',    '1931-03-26', '/ˈniːmɔɪ/'),
        (3, 'Nichelle Nichols', '1932-12-28', '/nɪˈʃɛl/')
    )
)

SELECT
    CAST(id AS INTEGER) AS id,
    CAST(name AS TEXT) AS name,
    CAST(date_of_birth AS TEXT) AS date_of_birth,
    CAST(surname_ipa AS TEXT) AS surname_ipa
FROM mock_data
;
