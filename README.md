This project studies the influence between social network and Wikidata ontology.

There are two main parts in this project. 

- ./src/ contains java code to parse Wikidata website for data.
- ./python/ directory contains analysis code written in Python notebooks

## Java Programs

In order to understand the source code, it may be a good idea to check out
the document at [wikisocial](https://github.com/FUB-HCC/wikisocial-paper)

This project use [Apache Maven](https://maven.apache.org) to manage the source code and build configuration.
The best way is to import it into IntelliJ IDEA.

Important programs to run is WTPNetworkGenerator and WtpNetworkSeriesGenerator.
Here is how to run them :

```java de.fu.info.wikisocial.executables.WTPNetworkGenerator ./data/owners_toc.txt ./data/wtp-networksv2/wtpnetwork_timestamp.csv```

and

```java de.fu.info.wikisocial.executables.WtpNetworkSeriesGenerator data/wtp-networksv2/wtpnetwork_timestamp.csv```

## Python Programs

Please see [Ipython](https://ipython.org) to know how to run a python
notebook.

Please note that there are two version of data sets for wtp-network: 
wtp-networks.zip and wtp-networksv2.zip. Please unzip them into the directory
data/ to use. wtp-networksv2 has more fields than wtp-networks.

