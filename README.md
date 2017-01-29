Aviation Safety - Semantic Technologies - UNIBZ
=====
Demo project for Semantic Technologies course in the Free University of Bolzano.

Contact information
---
* Aaron Estrada Poggio ([aaron.estrada.poggio@gmail.com](mailto:aaron.estrada.poggio@gmail.com)|[ruben.estrada@stud-inf.unibz.it](mailto:ruben.estrada@stud-inf.unibz.it) )
* Happy Das ([happy00701@gmail.com](mailto:happy00701@gmail.com))

Dataset
---
The NTSB aviation accident database contains information from 1962 and later about civil aviation accidents and selected incidents within the United States, its territories and possessions, and in international waters. Generally, a preliminary report is available online within a few days of an accident. Factual information is added when available, and when the investigation is completed, the preliminary report is replaced with a final description of the accident and its probable cause. Full narrative descriptions may not be available for dates before 1993, cases under revision, or where NTSB did not have primary investigative responsibility. More information on the following [link.](https://www.ntsb.gov/_layouts/ntsb.aviation/index.aspx)

Folder structure
---
```
/docs
    /ontology                           Ontology files (OWL and OBDA)
    /sql                                Dump files for database
/src
    /main
        /java                           Application classes
        /resources                      Resources and configuration for the application
            /queries                    Query templates for all models. All the queries are organized
                                        in different folders, according to the model they are used to
                /base                   Query templates for DefaultModel class (generic queries)     
                /filters                Query templates with filters to include in searches
            
            config.example.properties   Example for application configuration
/web
    /WEB-INF                            Web application content
        /tags                           Layouts for application
        /views                          Views used by servlets in the application classes
```


config.properties file
---
This file contains the configuration to connect to the SPARQL endpoint, the location for queries inside the project and the prefix configuration for querying. 

View src/main/resources/config.example.properties file as an example.

```
# Endpoint configuration
aviation_endpoint=<url_to_endpoint>

# Resource file paths
queries_path=queries/
queries_extension=qry
prefix_base_query=base/prefix_base

# Base query for instances
model_base_query=base/model_base
model_base_query_all=base/model_base_all
model_base_query_all_id=base/model_base_all_id

# Ontology prefixes
ontology_prefix=http://aviationsafety.unibz.it/
property_prefix=property/
resources_prefix=resources/
```