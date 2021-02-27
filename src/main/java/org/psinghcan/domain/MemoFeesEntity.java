package org.psinghcan.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="memo_fees")
public class MemoFeesEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="memo_fees_key",
            sequenceName="memo_fees_key",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="memo_fees_key")
    @Column(name = "memo_fees_id", updatable=false)
    private Long id;

    @Column(name="invoice_year")
    @Digits(integer = 4, fraction = 0)
    @NotNull
    private Integer invoiceYear;

    @Column(name="invoice_month")
    @Digits(integer = 4, fraction = 0)
    private Integer invoiceMonth;

    @Column(name="creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name="invoice_date")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @Column(name="franchise_id")
    @Digits(integer = 4, fraction = 0)
    private Integer franchiseId;

    @Size(max = 50)
    @Column(length = 50, name="franchise_number")
    private String franchiseNumber;

    @Size(max = 50)
    @Column(length = 50, name="franchise_state_province_id")
    private String franchiseStateProvinceId;

    @Size(max = 50)
    @Column(length = 50, name="franchise_country")
    private String franchiseCountry;

    @Size(max = 50)
    @Column(length = 50, name="am_status")
    private String amStatus;

    @Size(max = 50)
    @Column(length = 50, name="ar_cuid")
    private String arCuid;

    @Size(max = 50)
    @Column(length = 50, name="ar_invoice_number")
    private String arInvoiceNumber;

    @Digits(integer = 12,  fraction = 2)
    @Column(precision = 14, scale = 2, name="amount")
    private BigDecimal amount;

    @Digits(integer = 12,  fraction = 2)
    @Column(precision = 14, scale = 2, name="taxes")
    private BigDecimal taxes;

    @Column(name="ach_status_id")
    @Digits(integer = 4, fraction = 0)
    private Integer achStatusId;

    @Column(name="ecollect_status_id")
    @Digits(integer = 4, fraction = 0)
    private Integer ecollectStatusId;

    @Size(max = 20)
    @Column(length = 20, name="check_number")
    private String checkNumber;

    @Size(max = 20)
    @Column(length = 20, name="ar_receipt_number")
    private String arReceiptNumber;

    @Column(name="invoice_payment_date")
    @Temporal(TemporalType.DATE)
    private Date invoicePaymentDate;

    @Column(name="credit_status_id")
    @Digits(integer = 4, fraction = 0)
    private Integer creditStatusId;

    @Size(max = 20)
    @Column(length = 20, name="credit_invoice_number")
    private String creditInvoiceNumber;

    @Column(name="credit_invoice_date")
    @Temporal(TemporalType.DATE)
    private Date creditInvoiceDate;
}
