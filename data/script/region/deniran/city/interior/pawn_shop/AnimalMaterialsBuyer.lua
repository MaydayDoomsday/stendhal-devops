--[[
 ***************************************************************************
 *                    Copyright © 2020-2023 - Arianne                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************
]]


local buyer = nil
local buyerName = "Harley"


local function initNPC()
	buyer = entities:create({
		type = "SpeakerNPC",
		name = buyerName,
		description = "You see " .. buyerName .. ". He is employed at the pawn shop.",
		outfit = {
			layers = "body=0,head=0,eyes=0,hair=11,dress=53"
		},
		pos = {17, 21},
		dir = Direction.UP
	})

	-- dialogue
	buyer:addGreeting()
	buyer:addGoodbye()
	buyer:addOffer("Please check the blackboard for a list of items that I buy.")
	buyer:addHelp("I buy animal materials, and my boss over there, he buys all kinds of rings.")
	buyer:addQuest("No thanks. I don't need any help.")
	buyer:addJob("I'm working here at the pawn shop to try and save up for a home of my own. If I have a house, I am sure to get a girlfriend.")

	game:add(buyer)
end

local function initShop()
	-- shop sign
	local sign = entities:create({
		type = "ShopSign",
		name = "buyanimalmaterials",
		title = "Animal Materials Barter",
		caption = buyerName .. " buys the following items",
		seller = false
	})
	sign:setEntityClass("blackboard")
	sign:setPosition(19, 20)

	game:add(sign);
end


local zone = "int_deniran_pawn_shop"
if game:setZone(zone) then
	initNPC()
	if buyer ~= nil then
		initShop()
	end
else
	logger:error("Could not set zone: " .. zone)
end
