import Text.HTML.TagSoup
import Data.List
import qualified Data.Map as Map


{- 
  div can have any Flow element
<!ELEMENT div %Flow;>  <!-- generic language/style container -->
<!ATTLIST div
  %attrs;
  >
<!ENTITY % Flow "(#PCDATA | %block; | form | %inline; | %misc;)*">
Flow impliments Pcdata, block, Form, Inline, Misc
Block impliments P, Heading, 
-}

tagStartsWith :: String -> Tag -> Bool
tagStartsWith pre tag = case tag of
	TagOpen s _ -> isPrefixOf pre s
	TagText s -> isPrefixOf pre s
	_ -> False


load :: IO [Tag]
load = do
	text <- readFile "xhtml1-strict.dtd" 
	return (parseTags text)

toJava :: Map.Map String [String] -> [Tag] -> [String]
toJava cmap ( TagOpen _ ((name,""):_: _): _tags) = 
	["//// "++name++" ////" ,
	"interface ContentOf_"++name++" extends HtmlElement  {}" ,
	"public static Element_"++name++" "++name++"(ContentOf_"++name++"... els) {return new Element_"++name++"(ATTRIBUTE_OF_"++name++", els);}" ,
	"public static Element_"++name++" "++name++"(AttributeOf_"++name++"[] atts, ContentOf_"++name++"... els) {return new Element_"++name++"(atts, els);}" ,
	"private static final AttributeOf_"++name++"[] ATTRIBUTE_OF_"++name++" = new AttributeOf_"++name++"[0];" ,
	"interface AttributeOf_"++name++" extends IHtmlAttr  {}",
	elementClass, 
	"  Element_"++name++"(AttributeOf_"++name++"[] as, ContentOf_"++name++"...cs) {" ,
	"    super(\""++name++"\", as, cs);" ,
	"}}",
	""]
	where
		hasContentOf = Map.member name cmap
		elementClass = if hasContentOf 
			then "public static class Element_"++name++" extends HtmlTag implements "++delim (getContentOf)++" {"
			else "public static class Element_"++name++" extends HtmlTag  {"
		items :: [String]
		items = nub $cmap Map.! name
		getContentOf :: [String]
		getContentOf = map (\s -> "ContentOf_"++s) items
		delim:: [String] -> String
		delim = foldr1 (\s t -> (s++", "++t)) 

toJava _ _ =  ["No Match"]

sampleTagList :: [Tag]
sampleTagList = [TagOpen "!ELEMENT" [("html",""),("head",""),("body","")],TagText "\n",TagOpen "!ATTLIST" [("html",""),("i18n",""),("id",""),("ID",""),("IMPLIED",""),("xmlns",""),("URI",""),("FIXED",""),("","http://www.w3.org/1999/xhtml")],TagText "\n\n",TagComment "================ Document Head =======================================",TagText "\n\n",TagOpen "!ENTITY" [("head.misc",""),("","(script|style|meta|link|object)*")],TagText "\n\n",TagComment " content model is %head.misc; combined with a single\n     title and an optional base element in any order ",TagText "\n\n"]

elementList :: [Tag] -> [[Tag]]
elementList ts = partitions (~== TagOpen "!ELEMENT" []) ts 

entityList :: [Tag] -> [[Tag]]
entityList ts = partitions (~== TagOpen "!ENTITY" []) ts 

elementName :: [Tag] -> String
elementName ( TagOpen _ ((name,""):_): _) =  name
elementName _ =  ""

type ContentMap =  Map.Map String [String]

sampleContentMap :: ContentMap
sampleContentMap  = Map.fromList [
		("html", []), 
		("head", ["html"]), -- head is allowed inside html
		("title", ["html"]), -- title goes inside html
    ("div", ["Flow"]) -- how to expand flow?
		]

makeContentMap :: [[Tag]] -> ContentMap
makeContentMap els = 
		foldr (\cl m -> addContentMap (elementContentTags cl) m) Map.empty $ els
		where
			addContentMap :: (String, [String]) -> ContentMap -> ContentMap
			addContentMap (name, clist) cm = foldr (\c m -> appendMap c name m) cm clist
			elementContentTags :: [Tag] -> (String, [String])
			elementContentTags ( TagOpen _ ((name,""):xs): _) =  (name, map fst xs)
			elementContentTags _ =  ("",[])
	

appendMap :: (Ord k) => k -> a -> Map.Map k [a] -> Map.Map k [a]
appendMap k v m =  Map.insert k (v:Map.findWithDefault [] k m) m

sampleElementMap :: [(String, [Tag])]
sampleElementMap = [
  ("html",[TagOpen "!ELEMENT" [("html",""),("head",""),("body","")],TagText "\n",TagOpen "!ATTLIST" [("html",""),("i18n",""),("id",""),("ID",""),("IMPLIED",""),("xmlns",""),("URI",""),("FIXED",""),("","http://www.w3.org/1999/xhtml")],TagText "\n\n",TagComment "================ Document Head =======================================",TagText "\n\n",TagOpen "!ENTITY" [("head.misc",""),("","(script|style|meta|link|object)*")],TagText "\n\n",TagComment " content model is %head.misc; combined with a single\n     title and an optional base element in any order ",TagText "\n\n"]),
  ("head",[TagOpen "!ELEMENT" [("head",""),("head.misc",""),("title",""),("head.misc",""),("base",""),("head.misc",""),("base",""),("head.misc",""),("title",""),("head.misc","")],TagText "\n\n",TagOpen "!ATTLIST" [("head",""),("i18n",""),("id",""),("ID",""),("IMPLIED",""),("profile",""),("URI",""),("IMPLIED","")],TagText "\n\n",TagComment " The title element is not considered part of the flow of text.\n       It should be displayed, for example as the page header or\n       window title. Exactly one title is required per document.\n    ",TagText "\n"])
  ]

elementMap :: [[Tag]] -> [(String, [Tag])]
elementMap e = map (\t -> ((elementName t), t)) e

entityMap :: [[Tag]] -> [(String, [Tag])]
entityMap e = map (\t -> ((elementName t), t)) e


main :: IO ()
main = do 
	ts <- load
	let ents = entityList ts
	let els = elementList ts
	let cm = makeContentMap els
	let m = elementMap els
	let em = entityMap ents
	mapM_ (\t -> putStrLn (unlines (toJava cm t))) els
	print (lookup "body" m)
	print (lookup "html" m)

