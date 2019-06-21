package hu.dpc.openbank.tpp.acefintech.backend.controller;


import hu.dpc.openbank.tpp.acefintech.backend.enity.bank.SupportedBanks;
import hu.dpc.openbank.tpp.acefintech.backend.repository.BankRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user/v1/")
public class UserController {
    private BankRepository bankRepository;

    public UserController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping(path = "/banks", produces = "application/json")
    public SupportedBanks getSupportedBanks(@AuthenticationPrincipal User user) {
        SupportedBanks supportedBanks = new SupportedBanks();
        supportedBanks.setBankInfoList(bankRepository.getUserConnectedBanks(user.getUsername()));
        return supportedBanks;
    }
}
