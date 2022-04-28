package ulaval.glo2003.subjects;


import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import java.time.Clock;
import java.time.OffsetDateTime;

public final class OffsetDateTimeSubject extends Subject {

    private static final Subject.Factory<OffsetDateTimeSubject, OffsetDateTime>
            OFFSET_DATE_TIME_SUBJECT_FACTORY = OffsetDateTimeSubject::new;
    private final OffsetDateTime actual;

    private OffsetDateTimeSubject(FailureMetadata failureMetadata, OffsetDateTime subject) {
        super(failureMetadata, subject);
        this.actual = subject;
    }

    public static OffsetDateTimeSubject assertThat(String offsetDateTime) {
        return assertAbout(OFFSET_DATE_TIME_SUBJECT_FACTORY).that(
                OffsetDateTime.parse(offsetDateTime));
    }

    public static Subject.Factory<OffsetDateTimeSubject, OffsetDateTime> offsetdatetimes() {
        return OFFSET_DATE_TIME_SUBJECT_FACTORY;
    }

    public void isWithinExpectedRange() {
        OffsetDateTime now = OffsetDateTime.now(Clock.systemUTC());
        OffsetDateTime lowerBound = now.minusSeconds(2);
        OffsetDateTime higherBound = now.plusSeconds(2);

        if (actual.isBefore(lowerBound) || actual.isAfter(higherBound)) {
            failWithActual(simpleFact(
                    "expected to be within :\n (" + lowerBound + " and " + higherBound + ")"));
        }
    }
}
