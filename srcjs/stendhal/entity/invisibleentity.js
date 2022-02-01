/***************************************************************************
 *                   (C) Copyright 2003-2018 - Stendhal                    *
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU Affero General Public License as        *
 *   published by the Free Software Foundation; either version 3 of the    *
 *   License, or (at your option) any later version.                       *
 *                                                                         *
 ***************************************************************************/

"use strict";

var marauroa = window.marauroa = window.marauroa || {};

var InvisibleEntity = require("../../../build/ts/entity/InvisibleEntity").InvisibleEntity;

marauroa.rpobjectFactory["invisible_entity"] = InvisibleEntity;
marauroa.rpobjectFactory["area"] = InvisibleEntity;
marauroa.rpobjectFactory["looped_sound_source"] = InvisibleEntity;
marauroa.rpobjectFactory["tiled_entity"] = InvisibleEntity;
marauroa.rpobjectFactory["wall"] = InvisibleEntity;
marauroa.rpobjectFactory["blocktarget"] = InvisibleEntity;
marauroa.rpobjectFactory["flyover"] = InvisibleEntity;