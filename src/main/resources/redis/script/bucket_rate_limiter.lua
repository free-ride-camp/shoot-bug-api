local key = KEYS[1]
local nowMillis = tonumber(ARGV[1])
local ticksPerPeriod = tonumber(ARGV[2])
local periodDuration = tonumber(ARGV[3])
local lastReqMillis = redis.call('hget', key, 'lastReq')
local ticksLeft = redis.call('hget', key, 'bucketNum')
local result = false

if lastReqMillis == false then
    lastReqMillis = nowMillis
end

if ticksLeft == false then
    ticksLeft = ticksPerPeriod
end

local reqMillisDiff = nowMillis - lastReqMillis
local ticksPerMilli = ticksPerPeriod / periodDuration
local ticksToAdd = reqMillisDiff * ticksPerMilli

ticksLeft = math.min(ticksLeft + ticksToAdd, ticksPerPeriod) - 1

if ticksLeft < 0 then
    result = true
else
    redis.call('hset', key, 'lastReq', nowMillis)
    redis.call('hset', key, 'bucketNum', ticksLeft)
end

return result