package com.dg.eqs.core.progression.presetlevel

import com.dg.eqs.core.progression.presetlevel.roomdatabase.PresetLevelRoomEntity


class PresetLevelPreset {
    val presetVersion
        get() = 2

    val presetEntities
        get() = listOf(
                PresetLevelRoomEntity(1, exercise01, false, 0, 0),
                PresetLevelRoomEntity(2, exercise02, false, 0, 0),
                PresetLevelRoomEntity(3, exercise03, false, 0, 0),
                PresetLevelRoomEntity(4, exercise04, false, 0, 0),
                PresetLevelRoomEntity(5, exercise05, false, 0, 0),
                PresetLevelRoomEntity(6, exercise06, false, 0, 0),
                PresetLevelRoomEntity(7, exercise07, false, 0, 0),
                PresetLevelRoomEntity(8, exercise08, false, 0, 0),
                PresetLevelRoomEntity(9, exercise09, false, 0, 0),
                PresetLevelRoomEntity(10, exercise10, false, 0, 0),
                PresetLevelRoomEntity(11, exercise11, false, 0, 0),
                PresetLevelRoomEntity(12, exercise12, false, 0, 0),
                PresetLevelRoomEntity(13, exercise13, false, 0, 0),
                PresetLevelRoomEntity(14, exercise14, false, 0, 0),
                PresetLevelRoomEntity(15, exercise15, false, 0, 0),
                PresetLevelRoomEntity(16, exercise16, false, 0, 0),
                PresetLevelRoomEntity(17, exercise17, false, 0, 0),
                PresetLevelRoomEntity(18, exercise18, false, 0, 0),
                PresetLevelRoomEntity(19, exercise19, false, 0, 0),
                PresetLevelRoomEntity(20, exercise20, false, 0, 0),
                PresetLevelRoomEntity(21, exercise21, false, 0, 0),
                PresetLevelRoomEntity(22, exercise22, false, 0, 0),
                PresetLevelRoomEntity(23, exercise23, false, 0, 0),
                PresetLevelRoomEntity(24, exercise24, false, 0, 0),
                PresetLevelRoomEntity(25, exercise25, false, 0, 0),
                PresetLevelRoomEntity(26, exercise26, false, 0, 0),
                PresetLevelRoomEntity(27, exercise27, false, 0, 0),
                PresetLevelRoomEntity(28, exercise28, false, 0, 0),
                PresetLevelRoomEntity(29, exercise29, false, 0, 0),
                PresetLevelRoomEntity(30, exercise30, false, 0, 0),
                PresetLevelRoomEntity(31, exercise31, false, 0, 0),
                PresetLevelRoomEntity(32, exercise32, false, 0, 0),
                PresetLevelRoomEntity(33, exercise33, false, 0, 0),
                PresetLevelRoomEntity(34, exercise34, false, 0, 0),
                PresetLevelRoomEntity(35, exercise35, false, 0, 0),
                PresetLevelRoomEntity(36, exercise36, false, 0, 0),
                PresetLevelRoomEntity(37, exercise37, false, 0, 0),
                PresetLevelRoomEntity(38, exercise38, false, 0, 0),
                PresetLevelRoomEntity(39, exercise39, false, 0, 0),
                PresetLevelRoomEntity(40, exercise40, false, 0, 0))

    private val exercise01 get() = "=[+±[+x,+1],+2]"
    private val exercise02 get() = "=[+±[+1,+/[+1,+2]],+x]"
    private val exercise03 get() = "=[+±[+3,+x,-4],+10]"
    private val exercise04 get() = "=[+±[+99,-80,+20],-x]"
    private val exercise05 get() = "=[+±[-1000,-x,-3],-703]"
    private val exercise06 get() = "=[+±[+/[+2,+3],+x,+/[+1,+3]],+10]"
    private val exercise07 get() = "=[+±[+200,+/[+1,+2]],+±[+/[+1,+2],+x]]"
    private val exercise08 get() = "=[+±[+17,-3],+±[+3,-1,-x]]"
    private val exercise09 get() = "=[+±[+/[+1,+5],-/[+2,+5],-x],+0]"
    private val exercise10 get() = "=[+±[+x,-/[+1,+3],+/[+1,+4]],+/[+1,+2]]"
    private val exercise11 get() = "=[+±[+1,+*[+3,+4]],+x]"
    private val exercise12 get() = "=[+*[-1,+±[+x,+5,+2]],-10]"
    private val exercise13 get() = "=[+±[+*[-9,+5],+*[+3,+x]],+54]"
    private val exercise14 get() = "=[+*[+±[+x,-*[+5,+3]],+2],-40]"
    private val exercise15 get() = "=[+/[+*[+5,+x],+2],+25]"
    private val exercise16 get() = "=[+*[+±[+1,+x],+4],+/[+2,+3]]"
    private val exercise17 get() = "=[+*[+9,+±[+7,+/[+x,+10]]],+/[+639,+10]]"
    private val exercise18 get() = "=[+*[-33,+±[-5,-5],+±[-4,-4]],+x]"
    private val exercise19 get() = "=[+±[-x,+1],+*[+/[+2,+3],+/[+8,+9]]]"
    private val exercise20 get() = "=[+*[+±[+*[+±[+1,+2],+3],+4],+5],+x]"
    private val exercise21 get() = "=[+x,+/[+1,+/[+1,+2]]]"
    private val exercise22 get() = "=[+4,+±[+/[+x,+/[+1,+2]],+8]]"
    private val exercise23 get() = "=[+/[+x,+2],+±[+/[+1,+/[-1,+2]],+/[+/[+1,+2],-1]]]"
    private val exercise24 get() = "=[+/[+/[+x,+2],+/[+1,+2]],+23]"
    private val exercise25 get() = "=[+±[+4,+/[+3,+±[+5,+/[+1,+2]]]],+x]"
    private val exercise26 get() = "=[+±[+*[+2,+x],-2],+±[+*[-3,+±[-8,+4]],-8]]"
    private val exercise27 get() = "=[+±[+1,-1,-±[+2,+x],-1],+10]"
    private val exercise28 get() = "=[+99,+±[+/[+1,+2],-±[+2,+x],-/[+1,+2]]]"
    private val exercise29 get() = "=[+/[+±[+*[-2,+x],+7],+6],+/[+3,+2]]"
    private val exercise30 get() = "=[-1,+±[+*[+/[-1,+4],+x],+12]]"
    private val exercise31 get() = "=[+/[+1,+/[+1,+±[-3,+10]]],+±[+x,-10]]"
    private val exercise32 get() = "=[+/[+*[+/[+2,+3],+x],+2],+±[+/[+1,+2],-/[+1,+3]]]"
    private val exercise33 get() = "=[+*[+6,+±[+*[+6,+x],+7]],+5]"
    private val exercise34 get() = "=[+±[+*[+/[+5,+3],+x],+/[+7,+6]],-9]"
    private val exercise35 get() = "=[+±[+x,-/[+7,+5]],+±[+/[+/[+10,+3],+2],-54]]"
    private val exercise36 get() = "=[+±[-x,-±[+/[+3,+4],-/[+1,+4]]],+/[+1,+4]]"
    private val exercise37 get() = "=[+/[+x,+*[+10,+±[+6,+9]]],+/[+1,+300]]"
    private val exercise38 get() = "=[+/[+1,+/[+±[+2,+7],+*[+3,+4]]],+±[-x,-5]]"
    private val exercise39 get() = "=[+±[+/[-4,+15],+/[+3,+8]],+/[+±[+x,-3],+3]]"
    private val exercise40 get() = "=[+±[+/[+*[+8,+x],+5],+/[+±[-4,-3],+3]],+/[+3,+7]]"
}