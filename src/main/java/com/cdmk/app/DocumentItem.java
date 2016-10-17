package com.cdmk.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by javon on 19/08/2016.
 */
public class DocumentItem {

    private int startIndex;
    private int endIndex;
    private String content;

    public boolean presentInDocument;
    public boolean inCorrectPosition;
    public String tag;

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

    public HashMap<String, String> correctionMap = new HashMap<>(); // map to hold message when item is
    // incorrectly placed in document

    public HashMap<String, String> descriptionMap = new HashMap<>(); // map to hold message describing
    // the purpose of the section

    public HashMap<String, String> titleMap = new HashMap<>(); // map to hold the user friendly titles of
    // the regex used to identify the sections in a document


    // Used to govern the order in which section headers should be laid out in a DRP
    private static ArrayList<String> tags = new ArrayList<>(Arrays.asList(DocumentItem.EXPRESSION_FOREWORD
            , DocumentItem.EXPRESSION_EXECUTIVE_SUMMARY
            , DocumentItem.EXPRESSION_ACKNOWLEDGEMENTS
            , DocumentItem.EXPRESSION_GLOSSARY
            , DocumentItem.EXPRESSION_INTRODUCTION
            , DocumentItem.EXPRESSION_AUTHORITY
            , DocumentItem.EXPRESSION_CRITICAL_ASSUMPTIONS
            , DocumentItem.EXPRESSION_INSTITUTIONAL_FRAMEWORK
            , DocumentItem.EXPRESSION_CONCEPTS_OPERATIONS
            , DocumentItem.EXPRESSION_EMERGENCY_OPERATIONS
            , DocumentItem.EXPRESSION_THREATS_INCIDENTS
            , DocumentItem.EXPRESSION_APPENDIX));

     {
        // Foreward
        correctionMap.put(EXPRESSION_FOREWORD, "Recommended to be the first of the 8 suggested preliminaries that " +
                "a DRP should consist of.");
        descriptionMap.put(EXPRESSION_FOREWORD, "Ministerial Statement in support of plan.");
        titleMap.put(EXPRESSION_FOREWORD, "Foreword");

        // Executive Summary
        correctionMap.put(EXPRESSION_EXECUTIVE_SUMMARY, "Recommended to follow the Foreword and precede the " +
                "Acknowledgements.");
        descriptionMap.put(EXPRESSION_EXECUTIVE_SUMMARY, "Ministerial Statement in support of plan.");
        titleMap.put(EXPRESSION_EXECUTIVE_SUMMARY, "Executive Summary");

        // Acknowledgements
        correctionMap.put(EXPRESSION_ACKNOWLEDGEMENTS, "Recommended to follow the Executive Summary and precede the" +
                " following, if they exist: Name of the plan, Signature Page, Record of Review and Updates. If none" +
                " of the aforementioned exist it should precede the Glossary of terms");
        descriptionMap.put(EXPRESSION_ACKNOWLEDGEMENTS, "Recognition of individuals or agencies that have made a " +
                "contribution to the development of the plan\n");
        titleMap.put(EXPRESSION_ACKNOWLEDGEMENTS, "Acknowledgements");

        // Glossary
        correctionMap.put(EXPRESSION_GLOSSARY, "Recommended to follow all of the following, if they exist:" +
                " Name of the plan, Signature Page, Record of Review and Updates. If none of the aforementioned exist" +
                " it should follow the Acknowledgements and be the last of the 8 suggested preliminaries.");
        descriptionMap.put(EXPRESSION_GLOSSARY, "This section will list and explain terminology used in the plan.");
        titleMap.put(EXPRESSION_GLOSSARY, "Glossary");

        // Introduction
        correctionMap.put(EXPRESSION_INTRODUCTION, "Recommended to be the first section following the preliminaries.");
        descriptionMap.put(EXPRESSION_INTRODUCTION, "The introduction will comprise of the following four elements:\n" +
                "\n" +
                "1. Background\n" +
                "\n" +
                "- Will provide a general synopsis of the Ministry/Agency/Department as it relates to " +
                "Disaster Management in [COUNTRY]\n" +
                "\n" +
                "2. Purpose of the plan\n" +
                "\n" +
                "- Describes the purpose of the plan\n" +
                "\n" +
                "3. Scope\n" +
                "\n" +
                "*Extent of the coverage of the plan: island-wide (country-wide) coverage \n" +
                "*Establishes timeframe\n" +
                "*It will be executed in tandem with (what other plans)\n" +
                "*This plan is multi-hazard in scope\n" +
                "\n" +
                "4. Mission Statement\n" +
                "\n" +
                "5. Describe the mission statement as determined by the Disaster Management Advisory Committee " +
                "or Legislation\n");
        titleMap.put(EXPRESSION_INTRODUCTION, "Introduction");

        // Authority
        correctionMap.put(EXPRESSION_AUTHORITY, "Recommended to follow the Introduction and precede the " +
                "Critical Assumptions.");
        descriptionMap.put(EXPRESSION_AUTHORITY, "This section will establish the authority under which the" +
                " plan is being prepared.\n" +
                "It will list the Legislation, Agreement or the body with such authority (i.e. Cabinet)\n");
        titleMap.put(EXPRESSION_AUTHORITY, "Authority");

        // Critical Assumptions
        correctionMap.put(EXPRESSION_CRITICAL_ASSUMPTIONS, "Recommended to follow the Authority and precede the " +
                "Institutional Framework.");
        descriptionMap.put(EXPRESSION_CRITICAL_ASSUMPTIONS, "This section will describe a series of a number" +
                " of assumptions, which will be key to the successful execution of the plan. \n");
        titleMap.put(EXPRESSION_CRITICAL_ASSUMPTIONS, "Critical Assumptions");

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
        titleMap.put(EXPRESSION_INSTITUTIONAL_FRAMEWORK, "Institutional Framework");

        // Concepts of Operations
        correctionMap.put(EXPRESSION_CONCEPTS_OPERATIONS, "Recommended to follow the Institutional Framework and " +
                "precede the Emergency Operations.");
        descriptionMap.put(EXPRESSION_CONCEPTS_OPERATIONS, "Describes the process through which the plan will " +
                "be executed. It will describe the roles and functions of the operation cells i.e. NEOC, JOCC, EOC \n" +
                "Use of MOUs where there is a void/gap in the national capacity.\n");
        titleMap.put(EXPRESSION_CONCEPTS_OPERATIONS, "Concepts of Operations");

        // Emergency Operations
        correctionMap.put(EXPRESSION_EMERGENCY_OPERATIONS, "Recommended to follow the Concepts of Operations and " +
                "precede the Trans – Island/country Threats or Incidents.");
        descriptionMap.put(EXPRESSION_EMERGENCY_OPERATIONS, "This section will detail the following:" +
                "\n" +
                "1. Alert and Warning Mechanism, \n" +
                "\n" +
                "2. Notification of an Incident, \n" +
                "\n" +
                "3. Response Operations: \n" +
                "* Mobilization and deployment of emergency agencies\n" +
                "* Resource mobilization, access and deployment\n" +
                "* Interagency coordination and interoperability \n" +
                "* Monitoring and evaluation of the incident, \n" +
                "* Linkages with supporting plans and SOPS, \n" +
                "* Standard Operations governing key Emergency Response Functions (ERF) - Telecoms,\n" +
                "\n" +
                "4. Activation and Deactivation\n" +
                "\n" +
                "5. Recovery Operations\n");
        titleMap.put(EXPRESSION_EMERGENCY_OPERATIONS, "Emergency Operations");

        // Trans – Island/country Threats or Incidents
        correctionMap.put(EXPRESSION_THREATS_INCIDENTS, "Recommended to follow the Emergency Operations and " +
                "precede the Appendix/Appendices.");
        descriptionMap.put(EXPRESSION_THREATS_INCIDENTS, "This section will outline arrangements and response " +
                "mechanism in event that the national emergency services had to respond to incidents or threats " +
                "within the other CDEMA PS.\n");
        titleMap.put(EXPRESSION_THREATS_INCIDENTS, "Trans – Island/country Threats or Incidents");

        // Executive Summary
        correctionMap.put(EXPRESSION_APPENDIX, "Recommended to be the last section of a DRP preceding " +
                "any 'Other Considerations' if they exist.");
        descriptionMap.put(EXPRESSION_APPENDIX, "This section will contain the following:\n" +
                "*Linkages with other plans\n" +
                "*Checklists\n" +
                "*Notification /Call Out Procedures\n" +
                "*Contact Information\n" +
                "*List of resource requirements\n" +
                "*Job Aid (Status Boards etc)\n" +
                "*Maps – emergency routes, procedures\n");
        titleMap.put(EXPRESSION_APPENDIX, "Appendix");
    }

    public DocumentItem(String tag)
    {
        this(tag, -1, -1, null, false);
    }

    public DocumentItem(String tag, int startIndex, int endIndex, String content) {
        this(tag,startIndex,endIndex,content, true);
    }

    private DocumentItem(String tag, int startIndex, int endIndex, String content, boolean presentInDocument) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.content = content;
        this.presentInDocument = presentInDocument;
        this.tag = tag;
    }

    static String getSectionRegularExpression(String section)
    {
        return "(['*']['*']([^'*']*)[' ']*"+section+"[' ']*['*']['*'])|([' ']*"+section+"[' ']*(\n)?['=']+)|([' ']*"+section+"[' ']*(\n)?[-]+)";
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

    public String getCorrection(String section)
    {
        return correctionMap.get(section);
    }

    public String getDescription(String section)
    {
        return descriptionMap.get(section);
    }

    public String getTitle(String section)
    {
        return titleMap.get(section);
    }

    public HashMap<String, String> getCorrectionMap() {
        return correctionMap;
    }

    public HashMap<String, String> getDescriptionMap() {
        return descriptionMap;
    }

    public HashMap<String, String> getTitleMap() {
        return titleMap;
    }


    public boolean isPresentInDocument() {
        return presentInDocument;
    }

    public void setPresentInDocument(boolean presentInDocument) {
        this.presentInDocument = presentInDocument;
    }

    public boolean isInCorrectPosition() {
        return inCorrectPosition;
    }

    public void setInCorrectPosition(boolean inCorrectPosition) {
        this.inCorrectPosition = inCorrectPosition;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static ArrayList<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("Tag:").append(tag)
                .append(System.getProperty("line.separator"))
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
