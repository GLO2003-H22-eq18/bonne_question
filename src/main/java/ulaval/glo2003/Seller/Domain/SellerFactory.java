package ulaval.glo2003.Seller.Domain;

import ulaval.glo2003.Seller.Exceptions.*;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Utils.StringUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class SellerFactory {
    StringUtil stringUtil = new StringUtil();

    public Seller create(SellerRequest sellerRequest) {
        checkMissingParam(sellerRequest);
        checkInvalidParam(sellerRequest);

        return new Seller(sellerRequest.name, sellerRequest.bio, OffsetDateTime.now(Clock.systemUTC()),
                            LocalDate.parse(sellerRequest.birthDate), new ArrayList<>());
    }

    private void checkMissingParam(SellerRequest sellerRequest){
        if(sellerRequest.bio == null)
            throw new MissingSellerBioException();
        else if(sellerRequest.birthDate == null)
            throw new MissingSellerBirthdateException();
        else if(sellerRequest.name == null)
            throw new MissingSellerNameException();
    }

    private void checkInvalidParam(SellerRequest sellerRequest){
        validateName(sellerRequest.name);
        validateBio(sellerRequest.bio);
        validateBirthdate(sellerRequest.birthDate);
    }

    private void validateName(String name){
        if(stringUtil.removeEmptyChar(name).isEmpty())
            throw new InvalidSellerNameException();
    }

    private void validateBio(String bio){
        if(stringUtil.removeEmptyChar(bio).isEmpty())
            throw new InvalidSellerBioException();
    }

    private void validateBirthdate(String date){
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(date);
        } catch (DateTimeParseException error) {
            throw new InvalidSellerBirthdateException();
        }
        Period period = Period.between(birthDate, LocalDate.now());
        if(period.getYears() < 18)
            throw new InvalidSellerBirthdateException();
    }
}