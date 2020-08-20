package com.spots.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spots.dto.DataInfo;
import com.spots.dto.EntryInfo;
import com.spots.dto.FMatchInfo;
import com.spots.service.EntryService;

@CrossOrigin(origins = {"*"})
@RestController
public class EntryController {

	@Autowired
	EntryService Entry;
	
	@PostMapping(value="/FreeMatchRoom/entry")
	public int FreeMatchEntry(@RequestParam int team_entry_uid, @RequestParam int uid, @RequestParam int positionnum) {
		/*
		 * System.out.println("자리배치 시작!!!!!"); System.out.println("team_entry_uid = " +
		 * team_entry_uid); System.out.println("positionnum = " + positionnum);
		 */
		//System.out.println("uid = " + uid);
		if(positionnum == 1)
			return Entry.S1(team_entry_uid, uid);
		else if(positionnum == 2)
			return Entry.S2(team_entry_uid, uid);
		else if(positionnum == 3)
			return Entry.S3(team_entry_uid, uid);
		else if(positionnum == 4)
			return Entry.S4(team_entry_uid, uid);
		else if(positionnum == 5)
			return Entry.S5(team_entry_uid, uid);
		else if(positionnum == 6)
			return Entry.S6(team_entry_uid, uid);
		else if(positionnum == 7)
			return Entry.S7(team_entry_uid, uid);
		else if(positionnum == 8)
			return Entry.S8(team_entry_uid, uid);
		else if(positionnum == 9)
			return Entry.S9(team_entry_uid, uid);
		else if(positionnum == 10)
			return Entry.S10(team_entry_uid, uid);
		else if(positionnum == 11)
			return Entry.S11(team_entry_uid, uid);
		else if(positionnum == 12)
			return Entry.S12(team_entry_uid, uid);
		else if(positionnum == 13)
			return Entry.S13(team_entry_uid, uid);
		else
			return 0;
	}
	
	@PostMapping(value="/FreeMatchRoom/entry/change")
	public int FreeMatchEntryChange(@RequestParam int team_entry_uid_before, @RequestParam int uid, @RequestParam int positionnum_before, @RequestParam int positionnum_after, @RequestParam int team_entry_uid_after) {
		/*
		 * System.out.println("자리변경 시작!!!!!");
		 * System.out.println("team_entry_uid_before = " + team_entry_uid_before);
		 * System.out.println("team_entry_uid_after = " + team_entry_uid_after);
		 * System.out.println("positionnum_before = " + positionnum_before);
		 * System.out.println("positionnum_after = " + positionnum_after);
		 * System.out.println("uid = " + uid);
		 */
		if(positionnum_after == 1)
			Entry.S1(team_entry_uid_after, uid);
		else if(positionnum_after == 2)
			Entry.S2(team_entry_uid_after, uid);
		else if(positionnum_after == 3)
			Entry.S3(team_entry_uid_after, uid);
		else if(positionnum_after == 4)
			Entry.S4(team_entry_uid_after, uid);
		else if(positionnum_after == 5)
			Entry.S5(team_entry_uid_after, uid);
		else if(positionnum_after == 6)
			Entry.S6(team_entry_uid_after, uid);
		else if(positionnum_after == 7)
			Entry.S7(team_entry_uid_after, uid);
		else if(positionnum_after == 8)
			Entry.S8(team_entry_uid_after, uid);
		else if(positionnum_after == 9)
			Entry.S9(team_entry_uid_after, uid);
		else if(positionnum_after == 10)
			Entry.S10(team_entry_uid_after, uid);
		else if(positionnum_after == 11)
			Entry.S11(team_entry_uid_after, uid);
		else if(positionnum_after == 12)
			Entry.S12(team_entry_uid_after, uid);
		else if(positionnum_after == 13)
			Entry.S13(team_entry_uid_after, uid);
		
		
		if(positionnum_before == 1)
			Entry.D1(team_entry_uid_before, uid);
		else if(positionnum_before == 2)
			Entry.D2(team_entry_uid_before, uid);
		else if(positionnum_before == 3)
			Entry.D3(team_entry_uid_before, uid);
		else if(positionnum_before == 4)
			Entry.D4(team_entry_uid_before, uid);
		else if(positionnum_before == 5)
			Entry.D5(team_entry_uid_before, uid);
		else if(positionnum_before == 6)
			Entry.D6(team_entry_uid_before, uid);
		else if(positionnum_before == 7)
			Entry.D7(team_entry_uid_before, uid);
		else if(positionnum_before == 8)
			Entry.D8(team_entry_uid_before, uid);
		else if(positionnum_before == 9)
			Entry.D9(team_entry_uid_before, uid);
		else if(positionnum_before == 10)
			Entry.D10(team_entry_uid_before, uid);
		else if(positionnum_before == 11)
			Entry.D11(team_entry_uid_before, uid);
		else if(positionnum_before == 12)
			Entry.D12(team_entry_uid_before, uid);
		else if(positionnum_before == 13)
			Entry.D13(team_entry_uid_before, uid);
		return 0;
	}
	
	@PostMapping(value="/FreeMatchRoom/entry/cancel")
	public int FreeMatchEntryCancel(@RequestParam int team_entry_uid_before, @RequestParam int uid, @RequestParam int positionnum_before) {
		/*
		 * System.out.println("자리취소 시작!!!!!");
		 * System.out.println("team_entry_uid_before = " + team_entry_uid_before);
		 * System.out.println("positionnum_before = " + positionnum_before);
		 * System.out.println("uid = " + uid);
		 */
		if(positionnum_before == 1)
			Entry.D1(team_entry_uid_before, uid);
		else if(positionnum_before == 2)
			Entry.D2(team_entry_uid_before, uid);
		else if(positionnum_before == 3)
			Entry.D3(team_entry_uid_before, uid);
		else if(positionnum_before == 4)
			Entry.D4(team_entry_uid_before, uid);
		else if(positionnum_before == 5)
			Entry.D5(team_entry_uid_before, uid);
		else if(positionnum_before == 6)
			Entry.D6(team_entry_uid_before, uid);
		else if(positionnum_before == 7)
			Entry.D7(team_entry_uid_before, uid);
		else if(positionnum_before == 8)
			Entry.D8(team_entry_uid_before, uid);
		else if(positionnum_before == 9)
			Entry.D9(team_entry_uid_before, uid);
		else if(positionnum_before == 10)
			Entry.D10(team_entry_uid_before, uid);
		else if(positionnum_before == 11)
			Entry.D11(team_entry_uid_before, uid);
		else if(positionnum_before == 12)
			Entry.D12(team_entry_uid_before, uid);
		else if(positionnum_before == 13)
			Entry.D13(team_entry_uid_before, uid);
		return 0;
	}
	
	@PostMapping(value="/FreeMatchRoom/entrylist")
	public EntryInfo entrylist(@RequestParam int team_entry_uid) {
		/*
		 * System.out.println("리스트출력"); System.out.println("team_entry_uid = " +
		 * team_entry_uid);
		 */
		return Entry.entrylist(team_entry_uid);
	}
	
	
	@PostMapping(value="/FreeMatch/win")
	public int FreeMatchWin(FMatchInfo dto) {
		//System.out.println("이긴경우");
		return Entry.FWin(dto);
	}
	
	@PostMapping(value="/FreeMatch/lose")
	public int FreeMatchLose(FMatchInfo dto) {
		//System.out.println("진경우");
		return Entry.FLose(dto);
	}
	
	@PostMapping(value="/FreeMatch/draw")
	public int FreeMatchDraw(FMatchInfo dto) {
		//System.out.println("비긴경우");
		return Entry.FDraw(dto);
	}
	
		
	@PostMapping(value="/FreeMatchRoom/updateuser")
	public int FreeMatchupdate(DataInfo dto)
	{
		/*
		 * System.out.println("updateuser 시작"); System.out.println(dto);
		 */
		return Entry.Upuser(dto);
	}
	
	@PostMapping(value="/TeamMatch/win")
	public int TeamMatchWin(@RequestParam int h_uid, @RequestParam int a_uid) {
		//System.out.println("이긴경우");
		return Entry.TWin(h_uid, a_uid);
	}
	
	@PostMapping(value="/TeamMatch/lose")
	public int TeamMatchLose(@RequestParam int h_uid, @RequestParam int a_uid) {
		//System.out.println("진경우");
		return Entry.TLose(h_uid, a_uid);
	}
	
	@PostMapping(value="/TeamMatch/draw")
	public int TeamMatchDraw(@RequestParam int h_uid, @RequestParam int a_uid) {
		//System.out.println("비긴경우");
		return Entry.TDraw(h_uid, a_uid);
	}
	
	@PostMapping(value="/TeamMatchRoom/cancel")
	public int FreeMatchEntryCancel(@RequestParam int team_matching_uid, @RequestParam int away_team_uid) {
		//System.out.println("팀매칭자리취소 시작!!!!!");
		
		return Entry.TD(team_matching_uid, away_team_uid);
	}
	
	
}
