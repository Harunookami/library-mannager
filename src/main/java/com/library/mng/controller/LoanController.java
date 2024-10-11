package com.library.mng.controller;

import com.library.mng.DTO.RecordLoan;
import com.library.mng.model.LoanModel;
import com.library.mng.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping
    public LoanModel addLoan(@RequestBody RecordLoan loan) {
        return service.save(loan);

    }

    @GetMapping
    public List<LoanModel> listAllLoans() {
        return service.listAllLoans();
    }

    @GetMapping("/{id}")
    public LoanModel findById(@PathVariable Long id){
        LoanModel loan = service.getLoanById(id);
        if (loan == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found");
        }

        return service.getLoanById(id);
    }

    @PutMapping("/{id}")
    public LoanModel updateLoan(@PathVariable Long id, @RequestBody RecordLoan loan) {
        return service.updateLoan(id, loan);
    }

    @PutMapping("/return/{id}")
    public LoanModel returnLoan(@PathVariable Long id, @RequestBody RecordLoan loan) {
        return service.returnLoan(id, loan);
    }


    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        service.deleteLoanById(id);
    }


}
