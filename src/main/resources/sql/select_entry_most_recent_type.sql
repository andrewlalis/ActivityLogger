SELECT id, datetime(occurred_at, 'localtime') as occurred_at, entry_type, user
FROM entries
WHERE entry_type = ? AND user = ?
ORDER BY occurred_at DESC
LIMIT 1;