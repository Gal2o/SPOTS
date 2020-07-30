package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spots.dto.TMatchInfo;
import com.spots.dto.TeamInfo;
import com.spots.service.EntryService;
import com.spots.service.RankService;

@CrossOrigin(origins = {"http://192.168.0.30:3000", "http://localhost:3000"})
@RestController
public class EntryController {

	@Autowired
	EntryService Entry;
	
	@GetMapping(value="/FreeMatchRoom/entry")
	public int FreeMatchEntry(@RequestParam int team_entry_uid, @RequestParam int uid, int positionnum) {
		System.out.println("자리배치 시작");
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
	

}
