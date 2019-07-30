/* Gets a list of all entries that occurred after the most recent entry of a certain type, for a particular user. */
SELECT id, datetime(occurred_at, 'localtime') as occurred_at, entry_type, user
FROM entries
WHERE occurred_at >= (
  SELECT occurred_at
  FROM entries
  WHERE entry_type = ? AND user = ?
  ORDER BY occurred_at DESC
  LIMIT 1
)
  AND user = ?
ORDER BY occurred_at ASC;