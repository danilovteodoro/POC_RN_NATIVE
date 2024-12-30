//
//  ResultMouleBridge.m
//  RNProject
//
//  Created by Danilo Vieira Teodoro on 18/04/24.
//

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ResultModule, NSObject)
    RCT_EXTERN_METHOD(resultString:(NSString *) s)
    RCT_EXTERN_METHOD(resultObject:(NSDictionary *) o)
@end
