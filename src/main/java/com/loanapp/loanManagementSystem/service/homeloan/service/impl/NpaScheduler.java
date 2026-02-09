package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//
//@Component
//public class NpaScheduler {
//
//    @Autowired
//    private EmiScheduleRepository emiRepo;
//    @Autowired
//    private HomeLoanNpaRepository npaRepo;
//
//    @Scheduled(cron = "0 0 1 * * ?")
//    public void markNpa() {
//
//        List<EmiSchedule> emis = emiRepo.findAll();
//
//        for (EmiSchedule emi : emis) {
//            if ("PENDING".equals(emi.getEmiStatus())
//                    && emi.getDueDate().isBefore(LocalDate.now().minusDays(90))) {
//
//                HomeLoanNpa npa = new HomeLoanNpa();
//                npa.setHomeLoan(emi.getHomeLoan());
//                npa.setOverdueDays(90);
//                npa.setNpaStatus("SUB_STANDARD");
//                npa.setWrittenOff(false);
//                npa.setLastUpdated(LocalDateTime.now());
//
//                npaRepo.save(npa);
//            }
//        }
//    }
//}


@Component
public class NpaScheduler {

    private final NpaServiceImpl npaService;

    public NpaScheduler(NpaServiceImpl npaService) {
        this.npaService = npaService;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void runDailyNpaCheck() {
        npaService.checkAndMarkNpa();
    }
}

