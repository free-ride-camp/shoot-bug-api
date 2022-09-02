local key = KEYS[1]
local nowMillis = ARGV[1]
local ticksPerPeriod = ARGV[2]
local periodDuration = ARGV[3]
local lastReqMillis = redis.call('hget', key, 'lastReq')
local ticksLeft = redis.call('hget', key, 'bucketNum')

local periodsPast = (nowMillis - lastReqMillis) / periodDuration
local ticksToAdd = periodsPast * ticksPerPeriod

local result = false
local ticksAfterReq = ticksToAdd + ticksLeft - 1

if (ticksAfterReq < 0)
then result = true
else

end

return result