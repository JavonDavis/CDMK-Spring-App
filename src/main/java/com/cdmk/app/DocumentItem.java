package com.cdmk.app;

import java.util.HashMap;

/**
 * Created by javon on 19/08/2016.
 */
public class DocumentItem {

    private int startIndex;
    private int endIndex;
    private String content;

    private boolean presentInDocument;
    private boolean inCorrectPosition;

    public static String EXPRESSION_FOREWORD = "Foreword";
    public static String EXPRESSION_EXECUTIVE_SUMMARY = "Executive Summary";
    public static String EXPRESSION_ACKNOWLEDGEMENTS = "Acknowledgements";
    public static String EXPRESSION_GLOSSARY = "Glossary of Terms";
    public static String EXPRESSION_INTRODUCTION = "Introduction";
    public static String EXPRESSION_AUTHORITY = "Authority";
    public static String EXPRESSION_CRITICAL_ASSUMPTIONS = "Critical Assumptions";
    public static String EXPRESSION_INSTITUTIONAL_FRAMEWORK = "Institutional Framework";
    public static String EXPRESSION_CONCEPTS_OPERATIONS = "Concepts of Operations";
    public static String EXPRESSION_EMERGENCY_OPERATIONS = "Emergency Operations";
    public static String EXPRESSION_THREATS_INCIDENTS = "Trans – Island/country Threats or Incidents";
    public static String EXPRESSION_APPENDIX = "(Appendices|Appendix)";

    private static HashMap<String, String> correctionMap = new HashMap<>();
    private static HashMap<String, String> descriptionMap = new HashMap<>();

    static {
        // Foreward
        correctionMap.put(EXPRESSION_FOREWORD, "Recommended to be the first of the 8 suggested preliminaries that " +
                "a DRP should consist of.");
        descriptionMap.put(EXPRESSION_FOREWORD, "Ministerial Statement in support of plan.");

        // Executive Summary
        correctionMap.put(EXPRESSION_EXECUTIVE_SUMMARY, "Recommended to follow the Foreword and precede the " +
                "Acknowledgements.");
        descriptionMap.put(EXPRESSION_EXECUTIVE_SUMMARY, "Ministerial Statement in support of plan.");

        // Acknowledgements
        correctionMap.put(EXPRESSION_ACKNOWLEDGEMENTS, "Recommended to follow the Executive Summary and precede the" +
                " following, if they exist: Name of the plan, Signature Page, Record of Review and Updates. If none" +
                " of the aforementioned exist it should precede the Glossary of terms");
        descriptionMap.put(EXPRESSION_ACKNOWLEDGEMENTS, "Recognition of individuals or agencies that have made a " +
                "contribution to the development of the plan\n");

        // Glossary
        correctionMap.put(EXPRESSION_GLOSSARY, "Recommended to follow all of the following, if they exist:" +
                " Name of the plan, Signature Page, Record of Review and Updates. If none of the aforementioned exist" +
                " it should follow the Acknowledgements and be the last of the 8 suggested preliminaries.");
        descriptionMap.put(EXPRESSION_GLOSSARY, "This section will list and explain terminology used in the plan.");

        // Introduction
        correctionMap.put(EXPRESSION_INTRODUCTION, "Recommended to be the first section following the preliminaries.");
        descriptionMap.put(EXPRESSION_INTRODUCTION, "The introduction will comprise of the following four elements:\n" +
                "\n" +
                "\tBackground\n" +
                "\n" +
                "Will provide a general synopsis of the Ministry/Agency/Department as it relates to " +
                "Disaster Management in [COUNTRY]\n" +
                "\n" +
                "\n" +
                "Purpose of the plan\n" +
                "\t\n" +
                "Describes the purpose of the plan\n" +
                "\n" +
                "Scope\n" +
                "\n" +
                "•\tExtent of the coverage of the plan: island-wide (country-wide) coverage \n" +
                "\n" +
                "•\tEstablishes timeframe\n" +
                "\n" +
                "•\tIt will be executed in tandem with (what other plans)\n" +
                "\n" +
                "•\tThis plan is multi-hazard in scope\n" +
                "\n" +
                "\n" +
                "\n" +
                "Mission Statement\n" +
                "\n" +
                "Describe the mission statement as determined by the Disaster Management Advisory Committee " +
                "or Legislation\n");

        // Authority
        correctionMap.put(EXPRESSION_AUTHORITY, "Recommended to follow the Introduction and precede the " +
                "Critical Assumptions.");
        descriptionMap.put(EXPRESSION_AUTHORITY, "This section will establish the authority under which the" +
                " plan is being prepared.\n" +
                "It will list the Legislation, Agreement or the body with such authority (i.e. Cabinet)\n");

        // Critical Assumptions
        correctionMap.put(EXPRESSION_CRITICAL_ASSUMPTIONS, "Recommended to follow the Authority and precede the " +
                "Institutional Framework.");
        descriptionMap.put(EXPRESSION_CRITICAL_ASSUMPTIONS, "This section will describe a series of a number" +
                " of assumptions, which will be key to the successful execution of the plan. \n");

        // Institutional Framework
        correctionMap.put(EXPRESSION_INSTITUTIONAL_FRAMEWORK, "Recommended to follow the Critical Assumptions and " +
                "precede the Concepts of Operations.");
        descriptionMap.put(EXPRESSION_INSTITUTIONAL_FRAMEWORK, "This section outlines the organizational framework " +
                "for the development and implementation of the plan. It will articulate the composition of the " +
                "National Structure, its terms of reference and the institutional linkage with other agencies.\n" +
                "It will also illustrate the relationship between Ministry/Department/Agency and the relevant " +
                "response agencies.\n" +
                "Agencies involved; Roles and Responsibilities\n" +
                "Schematic representation of Organisational structure\n");

        // Concepts of Operations
        correctionMap.put(EXPRESSION_CONCEPTS_OPERATIONS, "Recommended to follow the Institutional Framework and " +
                "precede the Emergency Operations.");
        descriptionMap.put(EXPRESSION_CONCEPTS_OPERATIONS, "Describes the process through which the plan will " +
                "be executed. It will describe the roles and functions of the operation cells i.e. NEOC, JOCC, EOC \n" +
                "Use of MOUs where there is a void/gap in the national capacity.\n");

        // Emergency Operations
        correctionMap.put(EXPRESSION_EMERGENCY_OPERATIONS, "Recommended to follow the Concepts of Operations and " +
                "precede the Trans – Island/country Threats or Incidents.");
        descriptionMap.put(EXPRESSION_EMERGENCY_OPERATIONS, "This section will detail the following:\n" +
                "\n" +
                "Alert and Warning Mechanism, \n" +
                "Notification of an Incident, \n" +
                "Response Operations: \n" +
                "•\tMobilization and deployment of emergency agencies\n" +
                "•\tResource mobilization, access and deployment\n" +
                "•\tInteragency coordination and interoperability \n" +
                "•\tMonitoring and evaluation of the incident, \n" +
                "•\tLinkages with supporting plans and SOPS, \n" +
                "•\tStandard Operations governing key Emergency Response Functions (ERF) - Telecoms,\n" +
                "\n" +
                "Activation and Deactivation\n" +
                "Recovery Operations\n");

        // Trans – Island/country Threats or Incidents
        correctionMap.put(EXPRESSION_THREATS_INCIDENTS, "Recommended to follow the Emergency Operations and " +
                "precede the Appendix/Appendices.");
        descriptionMap.put(EXPRESSION_THREATS_INCIDENTS, "This section will outline arrangements and response " +
                "mechanism in event that the national emergency services had to respond to incidents or threats " +
                "within the other CDEMA PS.\n");

        // Executive Summary
        correctionMap.put(EXPRESSION_EXECUTIVE_SUMMARY, "Recommended to be the last section of a DRP preceding " +
                "any 'Other Considerations' if they exist.");
        descriptionMap.put(EXPRESSION_EXECUTIVE_SUMMARY, "This section will contain the following:\n" +
                "•\tLinkages with other plans\n" +
                "•\tChecklists\n" +
                "•\tNotification /Call Out Procedures\n" +
                "•\tContact Information\n" +
                "•\tList of resource requirements\n" +
                "•\tJob Aid (Status Boards etc)\n" +
                "•\tMaps – emergency routes, procedures\n");
    }


    public DocumentItem(int startIndex, int endIndex, String content) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.content = content;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("Start Index:").append(startIndex)
                .append(System.getProperty("line.separator"))
                .append("End Index:")
                .append(endIndex)
                .append(System.getProperty("line.separator"))
                .append("Content:")
                .append(content)
                .append(System.getProperty("line.separator"));
        return builder.toString();
    }
}
