package sn.dialibah.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * by osow on 22/05/17.
 * for dtracker
 */

@RestController
@RequestMapping("common")
public class CommonController {

	@GetMapping("ping")
	public String ping(){
		return "pong";
	}
}
